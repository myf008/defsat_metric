package com.defsat.metric.admin.dao.daointerface;

import java.util.List;

import com.defsat.metric.admin.dao.daoobject.InfluxdbDO;

public interface InfluxdbDAO {

	InfluxdbDO getInfluxdbById(String influxdbId);
	
	List<InfluxdbDO> getAllInfluxdb();

	void insert(InfluxdbDO influxdbDO);

	void delete(String influxdbId);

	void updateByInfluxdbId(InfluxdbDO influxdbDO);
}
