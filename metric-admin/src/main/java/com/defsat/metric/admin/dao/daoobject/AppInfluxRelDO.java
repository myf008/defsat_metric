package com.defsat.metric.admin.dao.daoobject;

import java.io.Serializable;
import java.util.Date;


public class AppInfluxRelDO implements Serializable{


	/**  */
	private static final long serialVersionUID = -3195938940107606021L;

	/** 自增主键 */
	private int id;

	/** app id */
	private String appId;
	
	/** influxdb id */
	private String influxdbId;
	
	/** 数据库名 */
	private String dbName;
	
	/** 保留策略 */
	private String retention;
	
	/** 创建时间 */
	private Date creatTime;

	/** 修改时间 */
	private Date modifyTime;
	

	public AppInfluxRelDO() {
		
	}

	public AppInfluxRelDO(String appId, String influxdbId, String dbName, String retention) {
		super();
		this.appId = appId;
		this.influxdbId = influxdbId;
		this.setDbName(dbName);
		this.setRetention(retention);
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAppId() {
		return appId;
	}


	public void setAppId(String appId) {
		this.appId = appId;
	}


	public String getInfluxdbId() {
		return influxdbId;
	}


	public void setInfluxdbId(String influxdbId) {
		this.influxdbId = influxdbId;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getRetention() {
		return retention;
	}

	public void setRetention(String retention) {
		this.retention = retention;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "AppInfluxRelDO [id=" + id + ", appId=" + appId + ", influxdbId=" + influxdbId + ", dbName=" + dbName
				+ ", retention=" + retention + ", creatTime=" + creatTime + ", modifyTime=" + modifyTime + "]";
	}


}
