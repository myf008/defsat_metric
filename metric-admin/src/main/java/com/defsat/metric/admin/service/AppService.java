package com.defsat.metric.admin.service;


import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.defsat.metric.admin.dao.daointerface.AppDAO;
import com.defsat.metric.admin.dao.daointerface.AppInfluxRelDAO;
import com.defsat.metric.admin.dao.daointerface.BlackListDAO;
import com.defsat.metric.admin.dao.daoobject.AppDO;

@Slf4j
@Service("appService")
public class AppService {

	@Autowired
	private AppDAO appDao;
	
	@Autowired
	private AppInfluxRelDAO appInfluxRelDao;
	
	@Autowired
	BlackListDAO blackListDAO;
	
	public AppDO getAppById(String appId){
		
		log.debug("get app, appId : {}", appId);
		
		return appDao.getAppById(appId);
	}
	
	public List<AppDO> getAllApps(){
		
		log.debug("get all apps.");
		
		return appDao.getAllApps();
	}
	
	
	@Transactional
	public void createApp(AppDO appDO){
		
		log.debug("create app, appDO : {} ", appDO.toString());
		
		appDao.insert(appDO);
	}
	
	@Transactional
	public void deleteApp(String appId){
		
		log.debug("delete app, appId : {}", appId);
		
		appDao.delete(appId);
		appInfluxRelDao.deleteRelation(appId);
		blackListDAO.delete(appId);
	}
	
	@Transactional
	public void updateByAppId(AppDO appDO){
		
		log.debug("update app, appId : {}", appDO.getAppId());
		
		appDao.updateByAppId(appDO);
	}
}
