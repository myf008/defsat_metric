package com.defsat.metric.storage;

import static com.defsat.metric.storage.StorageType.KAFKA;

import com.defsat.metric.IStorage;
import com.defsat.metric.config.MetricConfig;



public class StorageFactory {

	private StorageFactory() {

	}

	public static IStorage getStorage(MetricConfig config, IConnectorHandler connHandler) {

		switch (config.getStorageType()) {
			case KAFKA:
				return new KafkaStorage(config);
			case INFLUXDB:
			default:
				break;
		}
		return new InfluxdbStorage(connHandler);
	}
}
