package com.defsat.metric.core;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

import com.defsat.metric.config.MetricConfig;
import com.defsat.metric.util.SleepUtils;
import com.google.common.collect.Lists;

@Slf4j
public class RingBuffer<E> {
	private AtomicLong takeIndex = new AtomicLong(-1);
	private AtomicLong putIndex = new AtomicLong(-1);
	private int bufferSize;
	private E[] entries;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	@SuppressWarnings("unchecked")
	public RingBuffer(int bufferSize) {
		this.bufferSize = bufferSize;
		entries = (E[]) new Object[this.bufferSize];
	}
	
	@SuppressWarnings("unchecked")
	public RingBuffer(MetricConfig config) {
		this.bufferSize = config.getBufferSize();
		entries = (E[]) new Object[this.bufferSize];
	}

	public E getElement(int index) {
		return elementAt(index);
	}

	protected final E elementAt(int index) {
		E e = entries[index];
		return e;
	}

	public int getNextSlot(AtomicLong currentIndex) {
		return getNextSlot(currentIndex, 1);
	}

	public int getNextSlot(AtomicLong currentIndex, long n) {
		return (int) (currentIndex.addAndGet(n) % bufferSize);

	}

	public boolean put(E e) {
		boolean isDataCovered = false;
		int next = getNextSlot(putIndex);

		if (putIndex.get() - takeIndex.get() > bufferSize) {
			isDataCovered = true;
		}
		entries[next] = e;

		return isDataCovered;
	}

	public void put(List<E> list) {
		int length = list.size();
		for (int i = 0; i < length; i++) {
			put(list.get(i));
		}
	}

	public void putForWait(E e) {
		int next = getNextSlot(putIndex);

		while (putIndex.get() - takeIndex.get() > bufferSize) {
			SleepUtils.sleep(100);
			log.info("put wait 100 ms....");
		}
		entries[next] = e;
		
	}

	public boolean putForTimeout(E e, long timeout) {
		boolean isDataCovered = false;
		try {
			lock.lock();
			Date deadline = new Date(System.currentTimeMillis() + timeout);

			int next = getNextSlot(putIndex);
			if (putIndex.get() - takeIndex.get() > bufferSize) {
				try {
					condition.awaitUntil(deadline);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			if (putIndex.get() - takeIndex.get() > bufferSize) {
				isDataCovered = true;
			}
			entries[next] = e;

			return isDataCovered;

		} finally {
			lock.unlock();
		}
	}

	public E takeNextForWait() {
		return takeNextForTimeout(50L);
	}
	
	//timeout is not used
	public E takeNextForTimeout(long timeout) {

		long nextTake = takeIndex.incrementAndGet();
		int rIndex = (int)(nextTake % bufferSize);
		E e = entries[rIndex];

		if (e!=null) {
			entries[rIndex] = null;
			return e;
		}else{
			takeIndex.decrementAndGet();
			return null;
		}
	}

	private int getCurrSlot(AtomicLong currIndex) {
		return (int) (currIndex.get() % bufferSize);
	}

	public List<E> takeForWait(int n) {
		List<E> list = Lists.newArrayList();
		for (int i = 0; i < n; i++) {
			E e = takeNextForWait();
			if (null == e) {
				break;
			}
			list.add(e);
		}
		return list;
	}

	public List<E> takeForTimeout(int n, long timeout) {
		List<E> list = Lists.newArrayList();
		long singleTimeout = timeout / n;
		for (int i = 0; i < n; i++) {
			E e = takeNextForTimeout(singleTimeout);
			if (null == e) {
				break;
			}
			list.add(e);
		}
		return list;
	}

	public int getBufferSize() {
		return bufferSize;
	}

}