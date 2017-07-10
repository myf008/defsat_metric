package com.defsat.metric.config;

public class ConfigConstant {
	
	//influxdb
	public static final String ServerAddr = "metric.serverAddr";
	public static final String DBName = "metric.dbname";
	public static final String UserName = "metric.username";
	public static final String PassWord = "metric.password";
	public static final String Retention = "metric.retention";
	public static final String Consistency = "metric.consistency";
	public static final String BufferSize = "metric.bufferSize";
	public static final String StoragePolicy = "metric.storagePolicy";
	public static final String WorkerSize = "metric.workerSize";
	public static final String WorkerBufSize = "metric.workerBufSize";
	public static final String AppId = "metric.appId";
	
	public static final int BUFFER_SIZE = 4096;
	public static final int WORKER_SIZE = 4;	
	public static final int WORKER_BUFFER_SIZE = 10;
	
	public static final String METRIC_CLIENT_PROP = "def_metric.properties";
	public static final String USER_METRIC_CLIENT_PROP = "metric.properties";
	
	//kafka
	public static final String KafkaServers = "metric.kafka.bootstrap.servers";
	public static final String KafkaKeySerializer = "metric.kafka.key.serializer";
	public static final String KafkaValueSerializer = "metric.kafka.value.serializer";
	public static final String KafkaAcks = "metric.kafka.acks";
	public static final String KafkaRetries = "metric.kafka.retries";
	public static final String KafkaTopic = "metric.kafka.topic";
	

}
