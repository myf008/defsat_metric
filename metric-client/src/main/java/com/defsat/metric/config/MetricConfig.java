package com.defsat.metric.config;

import com.defsat.metric.storage.StorageType;


public class MetricConfig {

	private String serverAddr;
	
	private String username;
	
	private String password;
	
	private String dbName;
	
	private int bufferSize;
	
	private int workerSize;
	
	private int workerBufSize;
	
	private StorageType storageType;
	
	private String retention;
	
	private String consistent;
	
	private String appId;
	
	private KafkaProducerConfig kafkaConfig;
	
	public MetricConfig() {
		
	}


	public MetricConfig(String serverAddr, String username, String password, String dbName, int bufferSize,
			int workerSize, int workerBufSize, StorageType storageType, String retention, String consistent,
			String appId, String kafkaServers, String keySerializer, String valueSerializer, String acks, int retries,
			String topic) {
		this.serverAddr = serverAddr;
		this.username = username;
		this.password = password;
		this.dbName = dbName;
		this.bufferSize = bufferSize;
		this.workerSize = workerSize;
		this.workerBufSize = workerBufSize;
		this.setStorageType(storageType);
		this.retention = retention;
		this.consistent = consistent.toUpperCase();
		this.appId = appId;
		this.kafkaConfig = new KafkaProducerConfig(kafkaServers, keySerializer, valueSerializer,acks, retries,topic);
	}
	


	public String getServerAddr() {
		return serverAddr;
	}

	public void setServerAddr(String serverAddr) {
		this.serverAddr = serverAddr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public int getWorkerSize() {
		return workerSize;
	}

	public void setWorkerSize(int workerSize) {
		this.workerSize = workerSize;
	}

	public int getWorkerBufSize() {
		return workerBufSize;
	}

	public void setWorkerBufSize(int workerBufSize) {
		this.workerBufSize = workerBufSize;
	}
	

	public String getRetention() {
		return retention;
	}

	public void setRetention(String retention) {
		this.retention = retention;
	}

	public String getConsistent() {
		return consistent;
	}

	public void setConsistent(String consistent) {
		this.consistent = consistent;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public StorageType getStorageType() {
		return storageType;
	}

	public void setStorageType(StorageType storageType) {
		this.storageType = storageType;
	}


	public KafkaProducerConfig getKafkaConfig() {
		return kafkaConfig;
	}


	public void setKafkaConfig(KafkaProducerConfig kafkaConfig) {
		this.kafkaConfig = kafkaConfig;
	}


	@Override
	public String toString() {
		return "MetricConfig [serverAddr=" + serverAddr + ", username=" + username + ", password=" + password
				+ ", dbName=" + dbName + ", bufferSize=" + bufferSize + ", workerSize=" + workerSize
				+ ", workerBufSize=" + workerBufSize + ", storageType=" + storageType + ", retention=" + retention
				+ ", consistent=" + consistent + ", appId=" + appId + ", kafkaConfig=" + kafkaConfig.toString() + "]";
	}
	
	
	
}
