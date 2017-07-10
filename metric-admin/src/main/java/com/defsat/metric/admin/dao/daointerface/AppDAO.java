package com.defsat.metric.admin.dao.daointerface;

import java.util.List;

import com.defsat.metric.admin.dao.daoobject.AppDO;

public interface AppDAO {

	AppDO getAppById(String appId);
	
	List<AppDO> getAllApps();
	
	void insert(AppDO appDO);

	void delete(String appId);

	void updateByAppId(AppDO appDO);
}
