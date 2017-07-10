package com.defsat.metric.admin.dao.daointerface;

import java.util.List;

import com.defsat.metric.admin.dao.daoobject.AppInfluxRelDO;

public interface AppInfluxRelDAO {

	AppInfluxRelDO getRelation(String appId);
	
	List<AppInfluxRelDO> getAllRelations();
	
	void insert(AppInfluxRelDO appInfluxdbRelationDO);

	void deleteRelation(String appId);
	
	void delByInfluxdbId(String influxdbId);

	void updateRelation(AppInfluxRelDO appInfluxdbRelationDO);
}
