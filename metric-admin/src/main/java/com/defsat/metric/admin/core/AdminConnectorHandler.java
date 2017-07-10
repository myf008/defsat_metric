package com.defsat.metric.admin.core;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.defsat.metric.admin.config.ConfigConstant;
import com.defsat.metric.admin.dao.daoobject.AppInfluxRelDO;
import com.defsat.metric.admin.dao.daoobject.InfluxdbDO;
import com.defsat.metric.admin.service.AppInfluxRelService;
import com.defsat.metric.admin.service.InfluxdbService;
import com.defsat.metric.influxdb.InfluxdbConnector;
import com.defsat.metric.storage.IConnectorHandler;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Slf4j
@Component
public class AdminConnectorHandler implements IConnectorHandler {
	
	
	@Autowired
	private AppInfluxRelService relationService;
	
	@Autowired
	private InfluxdbService influxdbService;
	
	private LoadingCache <String, InfluxdbConnector> influxdbCache; 

	
	@PostConstruct
	public void init(){
		this.influxdbCache = CacheBuilder.newBuilder()
				.maximumSize(ConfigConstant.INFLUXDB_MAX_NUM)
				.refreshAfterWrite(ConfigConstant.INFLUXDB_REFRESH_INTERVAL, TimeUnit.MINUTES)
				.build(new CacheLoader<String,InfluxdbConnector>(){

					@Override
					public InfluxdbConnector load(String key) throws Exception {
						return getValue(key);
					}
				});
		
		initCache();
	}
	
	
	@Override
	public InfluxdbConnector getConnFromLocal(String appId) {
		return null;
	}

	@Override
	public InfluxdbConnector getConnFromRemote(String appId) {
		try {
			
			return influxdbCache.get(appId);
			
		} catch (ExecutionException e) {
			log.error("get influxdb connector failed for appId : {}",appId);
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public InfluxdbConnector getValue(String appId) {
		
		AppInfluxRelDO relationDo = this.relationService.getRelation(appId);
			
		return convertRelDoToConn(relationDo);
		
	}


	private InfluxdbConnector convertRelDoToConn(AppInfluxRelDO relationDo) {
		if(relationDo == null){
			return null;
		}
		String dbName = relationDo.getDbName();
		String retention = relationDo.getRetention();
		
		InfluxdbDO influxdbDo = this.influxdbService.getInfluxdb(relationDo.getInfluxdbId());
		if(influxdbDo == null){
			return null;
		}
		String addr = influxdbDo.getInfluxdbId();
		String userName = influxdbDo.getUserName();
		String password = influxdbDo.getPassword();
		String consistent = "ONE".toUpperCase();
		
		return new InfluxdbConnector(addr, userName, password, dbName, retention, consistent);
	}
	
	
	private void initCache(){
		
		List<AppInfluxRelDO> relationDoList = this.relationService.getAllRelations();
		for(AppInfluxRelDO relationDo : relationDoList){
			String appId = relationDo.getAppId();
			InfluxdbConnector  connector = convertRelDoToConn(relationDo);
			if(connector != null){
				this.influxdbCache.put(appId, connector);
			}
		}
	}

}
