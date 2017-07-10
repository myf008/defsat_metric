package com.defsat.metric.config;


import java.util.Properties;

public class KafkaProducerConfig {

	private String servers;
	private String keySerializer;
	private String valueSerializer;
	private String acks;
	private int retries;
	private String topic;
	
	public KafkaProducerConfig() {
		
	}

	public KafkaProducerConfig(String servers, String keySerializer, String valueSerializer, String acks, int retries,
			String topic) {
		this.servers = servers;
		this.keySerializer = keySerializer;
		this.valueSerializer = valueSerializer;
		this.acks = acks;
		this.retries = retries;
		this.topic = topic;
	}

	public Properties createkafkaProp(){
		Properties prop = new Properties();
		prop.put("bootstrap.servers", this.servers);
		prop.put("key.serializer", this.keySerializer);
		prop.put("value.serializer", this.valueSerializer);
		prop.put("acks", this.acks);
		prop.put("retries", this.retries);
		prop.put("topic", this.topic);
		
		return prop;
	}
	
	
	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public String getKeySerializer() {
		return keySerializer;
	}

	public void setKeySerializer(String keySerializer) {
		this.keySerializer = keySerializer;
	}

	public String getValueSerializer() {
		return valueSerializer;
	}

	public void setValueSerializer(String valueSerializer) {
		this.valueSerializer = valueSerializer;
	}

	public String getAcks() {
		return acks;
	}

	public void setAcks(String acks) {
		this.acks = acks;
	}

	public int getRetries() {
		return retries;
	}

	public void setRetries(int retries) {
		this.retries = retries;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "KafkaProducerConfig [servers=" + servers + ", keySerializer=" + keySerializer + ", valueSerializer="
				+ valueSerializer + ", acks=" + acks + ", retries=" + retries + ", topic=" + topic + "]";
	}

	
}
