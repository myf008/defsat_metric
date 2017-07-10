package com.defsat.metric.admin.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.defsat.metric.admin.dao.daointerface.AppInfluxRelDAO;
import com.defsat.metric.admin.dao.daoobject.AppInfluxRelDO;

@Slf4j
@Service("appInfluxRelService")
public class AppInfluxRelService {

	@Autowired
	private AppInfluxRelDAO appInfluxRelDao;
	
	
	public AppInfluxRelDO getRelation(String appId){
		
		log.debug("get app influxdb relation by appId " + appId);
		
		return appInfluxRelDao.getRelation(appId);
	}
	
	public List<AppInfluxRelDO> getAllRelations(){
		
		log.debug("get all app influxdb relation");
		
		return appInfluxRelDao.getAllRelations();
	}
	
	
	@Transactional
	public void createRelation(AppInfluxRelDO appInfluxdbRelationDO){
		
		log.debug("create app influxdb relation " + appInfluxdbRelationDO.toString());
		
		appInfluxRelDao.insert(appInfluxdbRelationDO);
	}
	
	@Transactional
	public void deleteRelation(String appId){
		
		log.debug("delete app influxdb relation by appId " + appId);
		
		appInfluxRelDao.deleteRelation(appId);
	}
	
	@Transactional
	public void delByInfluxdbId(String influxdbId){
		
		log.debug("delete app influxdb relation by influxdbId " + influxdbId);
		
		appInfluxRelDao.delByInfluxdbId(influxdbId);
	}
	
	@Transactional
	public void updateRelation(AppInfluxRelDO appInfluxRelDO){
		
		log.debug("update app influxdb relation by appId " + appInfluxRelDO.getAppId());
		
		appInfluxRelDao.updateRelation(appInfluxRelDO);
	}
}
