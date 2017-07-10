package com.defsat.metric.storage;


import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.defsat.metric.IStorage;
import com.defsat.metric.config.MetricConfig;
import com.defsat.metric.metric.MetricMessage;
import com.defsat.metric.util.SleepUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendAgentGroup {

	private boolean runing;

	private SendAgent[] workers;

	private AtomicLong position;
	
	private IStorage storage;

	public SendAgentGroup(MetricConfig config, IConnectorHandler connHandler) {
		this.workers = new SendAgent[config.getWorkerSize()];
		for (int i = 0; i < workers.length; i++) {
			this.workers[i] = new SendAgent(config.getWorkerBufSize());
		}
		this.position = new AtomicLong(0);
		this.storage = StorageFactory.getStorage(config,connHandler);
	}

	public void start() {
		if (!this.runing) {
			this.runing = true;
			for (int i = 0; i < workers.length; i++) {
				this.workers[i].setDaemon(true);
				this.workers[i].start();
			}
		}
	}

	public void stop() {
		this.runing = false;
	}

	/**
	 * 
	 * @param list
	 * @return 返回是否被覆盖，true代表丢失数据,false代表没有丢失数据
	 */
	public boolean put(MetricMessage msg) {
		int i = 0;
		boolean result = false;
		while (i < workers.length) {
			int index = (int) (position.incrementAndGet() % workers.length);
			// 返回真，表示成功放入队列
			result = workers[index].safePut(msg);

			if (result) {
				break;
			}
			i++;
		}

		if (!result) {
			return workers[new Random(workers.length).nextInt(workers.length)].forcePut(msg);
		}
		return false;
	}

	class SendAgent extends Thread {

		private BlockingQueue<MetricMessage> queue;

		public SendAgent(int bufSize) {
			this.queue = new LinkedBlockingQueue<MetricMessage>(bufSize);			
		}

		/**
		 * 
		 * @param list
		 * @return 返回true代表数据丢失
		 */
		public boolean forcePut(MetricMessage msg) {
			// 先尝试放一次
			if (this.safePut(msg)) {
				return false;
			} else {
				this.queue.remove();
				if (!this.queue.offer(msg)) {
					log.info("force offer fail.");
				}
			}
			return true;
		}

		/**
		 * 
		 * @param list
		 * @return 返回true代表成功放入
		 */
		public boolean safePut(MetricMessage msg) {
			return this.queue.offer(msg);
		}

		@Override
		public void run() {
			while (runing) {

				try {
					MetricMessage meticMsg = queue.poll(100,
							TimeUnit.MILLISECONDS);

					if (meticMsg == null) {
						SleepUtils.sleep(50);
					} else {
						// 发送数据
						storage.store(meticMsg);
						
					}
				} catch (Throwable e) {
					log.error("sendagent store fail.", e);
					SleepUtils.sleep(50);
				}

			}
		}

	}
}
