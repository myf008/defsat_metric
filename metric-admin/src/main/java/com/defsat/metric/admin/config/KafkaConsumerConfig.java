package com.defsat.metric.admin.config;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.base.Splitter;

@Component
public class KafkaConsumerConfig {

	private String servers;
	private String keyDeserializer;
	private String valueDeserializer;
	private String groupId;
	private boolean isAutoCommit;
	private int commitInterval;
	private String autoOffsetreset;
	private List<String> topics;
	private int consumerNum;
	
	
	
	public KafkaConsumerConfig() {
		
	}

	public KafkaConsumerConfig(String servers, String keyDeserializer, String valueDeserializer, String groupId,
			String isAutoCommit, String commitInterval, String autoOffsetreset, String topics, String consumerNum) {
		this.servers = servers;
		this.keyDeserializer = keyDeserializer;
		this.valueDeserializer = valueDeserializer;
		this.groupId = groupId;
		this.isAutoCommit = Boolean.getBoolean(isAutoCommit);
		this.setCommitInterval(Integer.parseInt(commitInterval));
		this.autoOffsetreset = autoOffsetreset;
		this.topics = Splitter.onPattern(",")
                			.omitEmptyStrings()  
                			.splitToList(topics);
		this.consumerNum = Integer.parseInt(consumerNum);
	}

	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public String getKeyDeserializer() {
		return keyDeserializer;
	}

	public void setKeyDeserializer(String keyDeserializer) {
		this.keyDeserializer = keyDeserializer;
	}

	public String getValueDeserializer() {
		return valueDeserializer;
	}

	public void setValueDeserializer(String valueDeserializer) {
		this.valueDeserializer = valueDeserializer;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public boolean isAutoCommit() {
		return isAutoCommit;
	}

	public void setAutoCommit(boolean isAutoCommit) {
		this.isAutoCommit = isAutoCommit;
	}

	public String getAutoOffsetreset() {
		return autoOffsetreset;
	}

	public void setAutoOffsetreset(String autoOffsetreset) {
		this.autoOffsetreset = autoOffsetreset;
	}

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}


	public int getConsumerNum() {
		return consumerNum;
	}

	public void setConsumerNum(int consumerNum) {
		this.consumerNum = consumerNum;
	}

	

	public int getCommitInterval() {
		return commitInterval;
	}

	public void setCommitInterval(int commitInterval) {
		this.commitInterval = commitInterval;
	}

	@Override
	public String toString() {
		return "KafkaConsumerConfig [servers=" + servers + ", keyDeserializer=" + keyDeserializer
				+ ", valueDeserializer=" + valueDeserializer + ", groupId=" + groupId + ", isAutoCommit="
				+ isAutoCommit + ", commitInterval=" + commitInterval + ", autoOffsetreset=" + autoOffsetreset
				+ ", topics=" + topics + ", consumerNum=" + consumerNum + "]";
	}
	
	
	
}
