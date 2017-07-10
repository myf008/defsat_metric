package com.defsat.metric.admin.core;


import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import com.defsat.metric.admin.config.ConfigConstant;
import com.defsat.metric.admin.config.KafkaConsumerConfig;
import com.defsat.metric.core.RingBuffer;
import com.defsat.metric.metric.MetricMessage;
import com.defsat.metric.util.ConfigUtil;
import com.defsat.metric.util.SleepUtils;


@Component
@Slf4j
public class KafkaReciever{
	private volatile boolean runing;
	
	@Autowired
	private KafkaConsumerConfig kafkaConfig;
	
	@Autowired
	private RingBuffer<MetricMessage> ringBuffer;
	
	@Autowired
	private AdminConsumerAgent consumerAgent;
	
	private ConsumerWorker[] kafkaWorkers;
	
	public KafkaReciever(){
		this.runing = false;
	}
	
	@PostConstruct
	public void init() {
		this.consumerAgent.start();
		this.kafkaWorkers = new ConsumerWorker[kafkaConfig.getConsumerNum()];
		Properties prop = ConfigUtil.getProperties(ConfigConstant.KAFKA_CONSUMER_PROP);
		for(int i=0; i<this.kafkaWorkers.length; i++){
			this.kafkaWorkers[i] = new ConsumerWorker(prop);
		}
		start();
	}
	
	public void start(){
		if(!this.runing){
			this.runing = true;
			log.info("start kafkaWorkers....");
			for(int i=0; i<this.kafkaWorkers.length; i++){
				this.kafkaWorkers[i].start();
			}
		}
	}
	
	public void stop() {
		this.runing = false;
		log.info("stop kafkaWorkers....");
		for(int i=0; i<this.kafkaWorkers.length; i++){
			this.kafkaWorkers[i].close();;
		}

	}

	public void put(final MetricMessage msg) {
		this.ringBuffer.putForWait(msg);
	}
	
	
	
	class ConsumerWorker extends Thread{
		
		KafkaConsumer<String,MetricMessage> consumer;
		
		public ConsumerWorker(Properties prop) {
			this.consumer = new KafkaConsumer<String, MetricMessage>(prop);
			this.consumer.subscribe(kafkaConfig.getTopics());
		}


		@Override
		public void run() {
			while(runing){
				try{
					ConsumerRecords<String, MetricMessage> records = consumer.poll(1000);  
				    for (TopicPartition partition : records.partitions()) {
		                 List<ConsumerRecord<String, MetricMessage>> partitionRecords = records.records(partition);
		                 for (ConsumerRecord<String, MetricMessage> record : partitionRecords) {
		                     log.info("offset : {}, value : {}", record.offset(), record.value());
		                     MetricMessage metricMsg = record.value();
						     put(metricMsg);
		                 }
//		                 long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
//		                 consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
		             }
				}catch(Exception e){
					log.error("consumerWorker send data fail!", e.getMessage());
					SleepUtils.sleep(3000);
				}
			}
		}
		
		public void close(){
			this.consumer.close();
		}
		
	}
	

}
