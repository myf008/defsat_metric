package com.defsat.metric.storage;

import com.defsat.metric.influxdb.InfluxdbConnector;

public interface IConnectorHandler {

	public InfluxdbConnector getConnFromLocal(String appId);
	
	public InfluxdbConnector getConnFromRemote(String appId);
}
