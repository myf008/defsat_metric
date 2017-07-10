package com.defsat.metric.admin.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.defsat.metric.admin.dao.daointerface.AppInfluxRelDAO;
import com.defsat.metric.admin.dao.daointerface.InfluxdbDAO;
import com.defsat.metric.admin.dao.daoobject.InfluxdbDO;

@Slf4j
@Service("influxdbService")
public class InfluxdbService {

	@Autowired
	private InfluxdbDAO influxdbDao;
	
	@Autowired
	private AppInfluxRelDAO appInfluxRelDao;
	
	
	public InfluxdbDO getInfluxdb(String influxdbId){
		
		log.debug("get influxdb of id " + influxdbId);
		
		return influxdbDao.getInfluxdbById(influxdbId);
	}
	
		
	public List<InfluxdbDO> getAllInfluxdb(){
		
		log.debug("get all influxdb");
		
		return influxdbDao.getAllInfluxdb();
	}
	
	@Transactional
	public void createInfluxdb(InfluxdbDO influxdbDO){
		
		log.debug("create influxdb " + influxdbDO.toString());
		
		influxdbDao.insert(influxdbDO);;
	}
	
	@Transactional
	public void deleteInfluxdb(String influxdbId){
		
		log.debug("delete influxdb with id " + influxdbId);
		
		influxdbDao.delete(influxdbId);
		appInfluxRelDao.delByInfluxdbId(influxdbId);
	}
	
	@Transactional
	public void updateByInfluxdbId(InfluxdbDO influxdbDO){
		
		log.debug("update influxdb with influxdb id " + influxdbDO.getInfluxdbId());
		
		influxdbDao.updateByInfluxdbId(influxdbDO);
	}
}
