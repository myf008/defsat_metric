package com.defsat.metric.storage;

import java.util.Map;

import com.defsat.metric.config.MetricConfig;
import com.defsat.metric.influxdb.InfluxdbConnector;
import com.google.common.collect.Maps;

public class LocalConnectorHandler implements IConnectorHandler {

	private Map<String, InfluxdbConnector> influxdbCache;
	
	public LocalConnectorHandler(MetricConfig config){
		String addr = config.getServerAddr();
		String username = config.getUsername();
		String password = config.getPassword();
		String dbname = config.getDbName();
		String retention = config.getRetention();
		String consistent = config.getConsistent();
		String appId = config.getAppId();
		this.influxdbCache = Maps.newConcurrentMap();
		this.influxdbCache.put(appId, new InfluxdbConnector(addr, username, password, dbname, retention, consistent));
	}
	
	@Override
	public InfluxdbConnector getConnFromLocal(String appId) {
		return influxdbCache.get(appId);
		
	}

	@Override
	public InfluxdbConnector getConnFromRemote(String appId) {
		return null;
	}
	

}
