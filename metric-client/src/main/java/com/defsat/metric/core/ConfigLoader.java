package com.defsat.metric.core;


import static com.defsat.metric.config.ConfigConstant.AppId;
import static com.defsat.metric.config.ConfigConstant.BUFFER_SIZE;
import static com.defsat.metric.config.ConfigConstant.BufferSize;
import static com.defsat.metric.config.ConfigConstant.Consistency;
import static com.defsat.metric.config.ConfigConstant.DBName;
import static com.defsat.metric.config.ConfigConstant.KafkaAcks;
import static com.defsat.metric.config.ConfigConstant.KafkaKeySerializer;
import static com.defsat.metric.config.ConfigConstant.KafkaRetries;
import static com.defsat.metric.config.ConfigConstant.KafkaServers;
import static com.defsat.metric.config.ConfigConstant.KafkaTopic;
import static com.defsat.metric.config.ConfigConstant.KafkaValueSerializer;
import static com.defsat.metric.config.ConfigConstant.PassWord;
import static com.defsat.metric.config.ConfigConstant.Retention;
import static com.defsat.metric.config.ConfigConstant.ServerAddr;
import static com.defsat.metric.config.ConfigConstant.StoragePolicy;
import static com.defsat.metric.config.ConfigConstant.UserName;
import static com.defsat.metric.config.ConfigConstant.WORKER_BUFFER_SIZE;
import static com.defsat.metric.config.ConfigConstant.WORKER_SIZE;
import static com.defsat.metric.config.ConfigConstant.WorkerBufSize;
import static com.defsat.metric.config.ConfigConstant.WorkerSize;

import com.defsat.metric.config.ConfigConstant;
import com.defsat.metric.config.KafkaProducerConfig;
import com.defsat.metric.config.MetricConfig;
import com.defsat.metric.storage.StorageType;
import com.defsat.metric.util.ConfigUtil;

public class ConfigLoader {

	public static MetricConfig loadMetricConfig(String propFileName, String userPropFileName){
		MetricConfig config = new MetricConfig();
		
		ConfigUtil.mergeConfig(propFileName, userPropFileName);
		config.setBufferSize(ConfigUtil.getInt(propFileName, BufferSize,BUFFER_SIZE));
		config.setConsistent(ConfigUtil.getString(propFileName, Consistency, "one").toUpperCase());
		config.setDbName(ConfigUtil.getString(propFileName, DBName, "default"));
		config.setUsername(ConfigUtil.getString(propFileName, UserName, "root"));
		config.setPassword(ConfigUtil.getString(propFileName, PassWord, "root"));
		config.setWorkerSize(ConfigUtil.getInt(propFileName, WorkerSize,WORKER_SIZE));
		config.setWorkerBufSize(ConfigUtil.getInt(propFileName, WorkerBufSize,WORKER_BUFFER_SIZE));
		config.setRetention(ConfigUtil.getString(propFileName, Retention, "default"));
		config.setServerAddr(ConfigUtil.getString(propFileName, ServerAddr, "default"));
		config.setStorageType(StorageType.getStorageType(ConfigUtil.getString(propFileName, StoragePolicy)));
		config.setAppId(ConfigUtil.getString(propFileName, AppId, ""));
		config.setKafkaConfig(loadKafkaConfig(propFileName));
		
		
		return config;
	}
	
	
	public static KafkaProducerConfig loadKafkaConfig(String propFileName){
		KafkaProducerConfig config = new KafkaProducerConfig();
		
		config.setServers(ConfigUtil.getString(propFileName, KafkaServers,""));
		config.setKeySerializer(ConfigUtil.getString(propFileName, KafkaKeySerializer,""));
		config.setValueSerializer(ConfigUtil.getString(propFileName, KafkaValueSerializer,""));
		config.setAcks(ConfigUtil.getString(propFileName, KafkaAcks,"0"));
		config.setRetries(Integer.parseInt(ConfigUtil.getString(propFileName, KafkaRetries,"1")));
		config.setTopic(ConfigUtil.getString(propFileName, KafkaTopic,""));
		
		return config;
	}
	
}
