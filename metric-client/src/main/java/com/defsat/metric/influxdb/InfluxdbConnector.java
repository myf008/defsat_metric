package com.defsat.metric.influxdb;

public class InfluxdbConnector{
	private String dbname;
	
	private String retention;
	
	private String consistent;
	
	private InfluxdbPool influxdbPool;
	
	public InfluxdbConnector(){
		
	}
	
	public InfluxdbConnector(String addr, String username, String password, String dbname, String retention,
			String consistent) {
		this.influxdbPool = new InfluxdbPool(addr, username, password);
		this.dbname = dbname;
		this.retention = retention;
		this.consistent = consistent;
	}

	
	
	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getRetention() {
		return retention;
	}

	public void setRetention(String retention) {
		this.retention = retention;
	}

	public String getConsistent() {
		return consistent;
	}

	public void setConsistent(String consistent) {
		this.consistent = consistent;
	}

	public InfluxdbPool getInfluxdbPool() {
		return influxdbPool;
	}

	public void setInfluxdbPool(InfluxdbPool influxdbPool) {
		this.influxdbPool = influxdbPool;
	}
	
	
}