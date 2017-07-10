package com.defsat.metric.admin.config;



import java.util.List;

import com.defsat.metric.util.ConfigUtil;
import com.google.common.base.Splitter;

public class AdminConfigLoader {

	public static KafkaConsumerConfig loadKafkaConfig(){
		KafkaConsumerConfig config = new KafkaConsumerConfig();
		String propFileName = ConfigConstant.KAFKA_CONSUMER_PROP;
		
		config.setServers(ConfigUtil.getString(propFileName, "bootstrap.servers",""));
		config.setKeyDeserializer(ConfigUtil.getString(propFileName, "key.deserializer",""));
		config.setValueDeserializer(ConfigUtil.getString(propFileName, "value.deserializer",""));
		config.setGroupId(ConfigUtil.getString(propFileName, "group.id",""));
		config.setAutoCommit(Boolean.getBoolean(ConfigUtil.getString(propFileName, "enable.auto.commit","")));
		config.setAutoOffsetreset(ConfigUtil.getString(propFileName, "auto.offset.reset",""));
		String topics = ConfigUtil.getString(propFileName, "topics","");
		List<String> topicList = Splitter.onPattern(",")
						    			.omitEmptyStrings()  
						    			.splitToList(topics);
		config.setTopics(topicList);
		
		return config;
	}
}
