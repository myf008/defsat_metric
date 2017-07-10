package com.defsat.metric.util;

import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;

import com.defsat.metric.metric.MetricMessage;
public class MetricMessageSerialier implements Serializer<MetricMessage>{

	private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

        String propertyName = isKey?"key.serializer.encoding":"value.serializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if(encodingValue == null) {
            encodingValue = configs.get("serializer.encoding");
        }

        if(encodingValue != null && encodingValue instanceof String) {
            this.encoding = (String)encodingValue;
        }
    }

    @Override
    public byte[] serialize(String topic, MetricMessage message) {
    	return message == null ? null : SerializationUtil.serialize(message);
    }

    @Override
    public void close() {

    }

}
