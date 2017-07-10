package com.defsat.metric.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;

import com.defsat.metric.config.KafkaProducerConfig;
import com.defsat.metric.config.MetricConfig;
import com.defsat.metric.metric.MetricMessage;


public class KafkaProducerService {

	private KafkaProducerConfig kafkaConfig;
	
	public KafkaProducerService(MetricConfig config){
		this.kafkaConfig = config.getKafkaConfig();
	}
	
	public KafkaProducer<String,MetricMessage> getKafkaProducer(){
		return new KafkaProducer<String,MetricMessage>(this.kafkaConfig.createkafkaProp());
	}
	
	public KafkaProducerConfig getKafkaConfig(){
		return this.kafkaConfig;
	}
}
