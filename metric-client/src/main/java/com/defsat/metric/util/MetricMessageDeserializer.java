package com.defsat.metric.util;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.defsat.metric.metric.MetricMessage;

public class MetricMessageDeserializer implements Deserializer<MetricMessage> {

    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

        String propertyName = isKey?"key.deserializer.encoding":"value.deserializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if(encodingValue == null) {
            encodingValue = configs.get("deserializer.encoding");
        }

        if(encodingValue != null && encodingValue instanceof String) {
            this.encoding = (String)encodingValue;
        }

    }

    @Override
    public MetricMessage deserialize(String topic, byte[] bytes) {
    	return bytes.length==0 ? null: SerializationUtil.deserialize(bytes, MetricMessage.class);
    }

    @Override
    public void close() {

    }
}
