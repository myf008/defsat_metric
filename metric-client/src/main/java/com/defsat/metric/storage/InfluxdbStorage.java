package com.defsat.metric.storage;

import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;

import com.defsat.metric.IStorage;
import com.defsat.metric.influxdb.InfluxdbConnector;
import com.defsat.metric.influxdb.InfluxdbConvertUtil;
import com.defsat.metric.influxdb.InfluxdbPool;
import com.defsat.metric.metric.MetricMessage;

@Slf4j
public class InfluxdbStorage implements IStorage{

	protected IConnectorHandler connectorHandler;
	
	public InfluxdbStorage(IConnectorHandler connectorHandler) {
		this.connectorHandler = connectorHandler;
	}
	
	protected void sendData(InfluxdbPool pool,BatchPoints batchPoints) throws Exception{
		
		InfluxDB influxdb = null;
		try {
			influxdb = pool.lease(2000);
			influxdb.write(batchPoints);
		} catch (Exception e) {

			throw e;
		}finally{
			if(influxdb != null){
				pool.release(influxdb);
			}
		}
	}

	@Override
	public void store(MetricMessage metricMsg) {
		InfluxdbConnector conn = null;
		
		String appId =metricMsg.getMetricHeader().getAppId();
		conn = connectorHandler.getConnFromLocal(appId);
		if(conn == null){
			conn = connectorHandler.getConnFromRemote(appId);
		}
		if(conn == null){
			log.warn("warn : cannot get a influxdb for appId :" + appId);
			return;
		}
		BatchPoints batchPoints = InfluxdbConvertUtil.toBatchPoints(conn.getDbname(),
				conn.getRetention(), conn.getConsistent(), metricMsg.getMetricDataList());
		InfluxdbPool pool = conn.getInfluxdbPool();
		int i = 0;
		
		while ((i++) < 3) {
			try {
				sendData(pool, batchPoints);
				break;
			} catch (Exception e) {
				log.error("send data fail,server=" + pool.getAddr(), e.getMessage());
			}
		}
		
	}
	

}
