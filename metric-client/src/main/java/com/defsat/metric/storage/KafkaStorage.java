package com.defsat.metric.storage;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.defsat.metric.IStorage;
import com.defsat.metric.config.KafkaProducerConfig;
import com.defsat.metric.config.MetricConfig;
import com.defsat.metric.kafka.KafkaProducerService;
import com.defsat.metric.metric.MetricMessage;

@Slf4j
public class KafkaStorage implements IStorage{
	
	private KafkaProducer<String,MetricMessage> kafkaProducer;
	private KafkaProducerConfig kafkaConfig;

	
	public KafkaStorage(MetricConfig config){
		KafkaProducerService kafkaService = new KafkaProducerService(config);
		kafkaProducer = kafkaService.getKafkaProducer();
		kafkaConfig = kafkaService.getKafkaConfig();
		log.info("-metric kafka producer config : {}", kafkaConfig.toString());
	}
	

	@Override
	public void store(MetricMessage message) {
		kafkaProducer.send(new ProducerRecord<String,MetricMessage>(kafkaConfig.getTopic(),message));
	}
	
	

}
