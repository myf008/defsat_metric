package com.defsat.metric.core;

import static com.defsat.metric.config.ConfigConstant.BUFFER_SIZE;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;

import com.defsat.metric.IMetricAgent;
import com.defsat.metric.config.MetricConfig;
import com.defsat.metric.metric.MetricData;

@Slf4j
public class ProducerAgent implements IMetricAgent {
	private RingBuffer<MetricData> ringBuffer;
	private MetricConfig metricConfig;
	
	
	public ProducerAgent(RingBuffer<MetricData> ringBuffer, MetricConfig config) {
		this.ringBuffer = ringBuffer;
		this.metricConfig = config;
	}

	public ProducerAgent() {
		this.ringBuffer = new RingBuffer<MetricData>(BUFFER_SIZE);
	}
	

	public void put(final MetricData data) {
		if(this.ringBuffer.put(data)){
			log.info("miss data!");
		}
	}

	
	@Override
	public void log(String name, String metric,Map<String, String> tags, long value) {
		log(name,metric, tags, System.currentTimeMillis(), value);
	}

	@Override
	public void log(String name, String metric,Map<String, String> tags, long time, long value) {
		MetricData metricData = new MetricData(name,metric, tags, time, value);
		put(metricData);
	}

	@Override
	public void log(String name, String metric,Map<String, String> tags, int value) {
		log(name, metric,tags, System.currentTimeMillis(), value);
	}

	@Override
	public void log(String name, String metric,Map<String, String> tags, long time, int value) {
		MetricData metricData = new MetricData(name,metric, tags, time, value);
		put(metricData);
	}

	@Override
	public void log(String name, String metric,Map<String, String> tags, double value) {
		log(name, metric,tags, System.currentTimeMillis(), value);
	}

	@Override
	public void log(String name, String metric,Map<String, String> tags, long time,
			double value) {
		MetricData metricData = new MetricData(name,metric, tags, time, value);
		put(metricData);
	}

	@Override
	public void log(String name, String metric,Map<String, String> tags, float value) {
		log(name, metric,tags, System.currentTimeMillis(), value);
	}

	@Override
	public void log(String name, String metric,Map<String, String> tags, long time,
			float value) {
		MetricData metricData = new MetricData(name,metric, tags, time, value);
		put(metricData);
	}

	@Override
	public void log(String name, String metric, Map<String, String> tags,
			String value) {
		log(name, metric,tags, System.currentTimeMillis(), value);
		
	}

	@Override
	public void log(String name, String metric, Map<String, String> tags,
			long time, String value) {
		MetricData metricData = new MetricData(name,metric, tags, time, value);
		put(metricData);
	}

	@Override
	public void log(String name, Map<String, String> tags,
			Map<String, Object> fields) {
		log(name, tags, fields, System.currentTimeMillis());
		
	}

	@Override
	public void log(String name, Map<String, String> tags,
			Map<String, Object> fields, long time) {
		MetricData metricData = new MetricData(name, tags, fields, time);
		put(metricData);
	}

	

}
