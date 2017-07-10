package com.defsat.metric.admin.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.defsat.metric.admin.core.KafkaReciever;
import com.defsat.metric.metric.MetricData;
import com.defsat.metric.metric.MetricHeader;
import com.defsat.metric.metric.MetricMessage;
import com.defsat.metric.util.SerializationUtil;
import com.defsat.metric.util.SleepUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class KafkaTest {

//	@Autowired
//	KafkaReciever consumer;
//	
//	
//	@Test
//	public void testMetricConsumer(){
//		consumer.start();
////		produceKafkaMessage();
//		
//		SleepUtils.sleep(1000000000);
//	}
	
	@Test
	public void testKafkaConsumer(){
		produceKafkaMessage();
		consumeKafaMessage();
		
		SleepUtils.sleep(100000000);
	}
	
	private void produceKafkaMessage(){
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.242.141:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "com.guard.monitor.util.MetricMessageSerialier");
		props.put("acks", "0");
		props.put("retries", 1);
		props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
		
		String topic = "myf";
		
		MetricMessage msg = getSerialMsg();
		log.info("topic: {}",topic);
		
		Producer<String,MetricMessage> producer = new KafkaProducer<String,MetricMessage>(props);
		producer.send(new ProducerRecord<String,MetricMessage>(topic,"producerMsg",msg));
//		 Future<RecordMetadata> future=  producer.send(new ProducerRecord<String, MetricMessage>("producerMsg",msg));
//	        try {
//	            RecordMetadata metadata= future.get(30, TimeUnit.SECONDS);
//                log.info("Send message: {topic:"+metadata.topic()+",partition:"+metadata.partition()+",offset:"+metadata.offset()+"}");
//	        } catch (Exception e) {
//	            throw new RuntimeException("Send message error: "+msg,e);
//	        }
		producer.flush();
		producer.close();
	}

	private MetricMessage getSerialMsg() {
		List<MetricData> dataList = Lists.newArrayList();
		Map<String,String> tags = Maps.newHashMap();
		tags.put("name", "s1");
		MetricData data1 = new MetricData("storage","value",tags,System.currentTimeMillis(),6);
		MetricData data2 = new MetricData("storage","value2",tags,System.currentTimeMillis(),7);
		dataList.add(data1);
		dataList.add(data2);
		MetricMessage msg = new MetricMessage(new MetricHeader("toa.robo"), dataList);
		return msg;
	}
	
	private void consumeKafaMessage(){
		Properties props = new Properties();
		props.put("bootstrap.servers","192.168.242.141:9092");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "com.guard.monitor.util.MetricMessageDeserializer");
		props.put("group.id", "test");
		props.put("enable.auto.commit", "true");
		props.put("auto.offset.reset", "earliest");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
		
		List<String> topicList = new ArrayList<String>();
		topicList.add("myf");
		Consumer<String,MetricMessage> consumer = new KafkaConsumer<String,MetricMessage>(props);
		consumer.subscribe(topicList);

		while(true) {  
		    ConsumerRecords<String, MetricMessage> records = consumer.poll(10000);  
		    System.out.println(records.count());  
		    for (ConsumerRecord<String, MetricMessage> record : records) {  
//		        consumer.seekToBeginning(new TopicPartition(record.topic(), record.partition())); 
		        MetricMessage metricMsg = record.value();
		        log.debug("record : {}",metricMsg.toString()); 
		    	System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
		    }  
		}  
	}
	
	
}
