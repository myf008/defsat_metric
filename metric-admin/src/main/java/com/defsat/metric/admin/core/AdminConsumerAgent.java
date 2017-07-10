package com.defsat.metric.admin.core;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.defsat.metric.admin.filter.AdminMetricFilter;
import com.defsat.metric.core.RingBuffer;
import com.defsat.metric.metric.MetricMessage;
import com.defsat.metric.storage.SendAgentGroup;
import com.defsat.metric.util.SleepUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AdminConsumerAgent implements Runnable{
	private volatile boolean runing;
	
	
	@Autowired
	private RingBuffer<MetricMessage> ringBuffer;
	
	@Autowired
	private AdminMetricFilter metricFileter;
	
	@Autowired
	private SendAgentGroup sendGroup;
	
	
	public AdminConsumerAgent(){
		this.runing = false;
	}
	
	public void start(){
		if(!this.runing){
			this.runing = true;
			new Thread(this).start();
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
		
			List<MetricMessage> list = this.ringBuffer.takeForTimeout(100, 1000);
			
			if(list.size() != 0){
				List<MetricMessage> metricMsgList = this.metricFileter.doFilterMessage(list);
				
				for(MetricMessage msg : metricMsgList){
					if(sendGroup.put(msg)){
						log.info("miss data size:{}", msg.getMetricDataList().size());
					}
				}
				
			}else{
				SleepUtils.sleep(50);
			}
			
		}
		
	}
	
}

