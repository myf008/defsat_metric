package com.defsat.metric.core;

import com.defsat.metric.IMetricAgent;
import com.defsat.metric.config.MetricConfig;
import com.defsat.metric.filter.ClientMetricFilter;
import com.defsat.metric.metric.MetricData;

public class AgentBuilder {
	
	private MetricConfig config;
	
	public AgentBuilder(MetricConfig config){
		this.config = config;
	}
	
	public AgentBuilder(){
		
	}
	
	public AgentBuilder config(MetricConfig config){
		this.config = config;
		return this;
	}
	
	public IMetricAgent build(){
		RingBuffer<MetricData> ringBuffer = new RingBuffer<MetricData>(this.config.getBufferSize());
		ConsumerAgent consumerAgent = new ConsumerAgent(ringBuffer,this.config, new ClientMetricFilter(this.config));
		consumerAgent.start();
		return new ProducerAgent(ringBuffer,this.config);
	}

}
