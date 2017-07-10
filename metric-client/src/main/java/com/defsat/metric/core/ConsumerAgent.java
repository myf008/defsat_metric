package com.defsat.metric.core;


import java.util.List;

import com.defsat.metric.config.MetricConfig;
import com.defsat.metric.filter.IMetricFilter;
import com.defsat.metric.metric.MetricData;
import com.defsat.metric.metric.MetricMessage;
import com.defsat.metric.storage.LocalConnectorHandler;
import com.defsat.metric.storage.SendAgentGroup;
import com.defsat.metric.util.SleepUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ConsumerAgent implements Runnable{

	private RingBuffer<MetricData> ringBuffer;
	private volatile boolean runing;
	private SendAgentGroup sendGroup;
	private IMetricFilter metricFileter;
	
	public ConsumerAgent(RingBuffer<MetricData> ringBuffer, MetricConfig config, IMetricFilter metricFileter) {
		this.ringBuffer = ringBuffer;
		this.sendGroup = new SendAgentGroup(config, new LocalConnectorHandler(config));
		this.runing = false;
		this.metricFileter = metricFileter;
	}
	
	public void start(){
		if(!this.runing){
			this.runing = true;
			Thread t = new Thread(this);
			t.setDaemon(true);
			t.start();
			sendGroup.start();
		}
	}
	
	public void stop(){
		this.runing = false;
		this.sendGroup.stop();
	}

	@Override
	public void run() {
		while(runing){
		
			List<MetricData> list = this.ringBuffer.takeForTimeout(500, 5000);
			
			if(list.size() != 0){
				MetricMessage metricMsg = this.metricFileter.doFilterData(list);
				
				if(sendGroup.put(metricMsg)){
					log.info("miss data size:{}",metricMsg.getMetricDataList().size());
				}
			}else{
				SleepUtils.sleep(50);
			}
			
		}
		
	}
	
}
