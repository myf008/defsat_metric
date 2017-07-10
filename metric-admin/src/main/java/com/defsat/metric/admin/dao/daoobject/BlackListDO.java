package com.defsat.metric.admin.dao.daoobject;

import java.io.Serializable;
import java.util.Date;


public class BlackListDO implements Serializable{

	/**  */
	private static final long serialVersionUID = -5786150014439810179L;

	/** 自增主键 */
	private Integer id;

	/** app id */
	private String appId;
	
	/** 数据库名 */
	private String dbName;
	
	/** 表名 */
	private String measurement;
	
	/** 字段名 */
	private String fieldName;
	
	/** 创建时间 */
	private Date creatTime;

	/** 修改时间 */
	private Date modifyTime;

	public BlackListDO(){
		
	}


	public BlackListDO(String appId, String dbName, String measurement, String fieldName) {
		this.appId = appId;
		this.dbName = dbName;
		this.measurement = measurement;
		this.fieldName = fieldName;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getAppId() {
		return appId;
	}


	public void setAppId(String appId) {
		this.appId = appId;
	}



	public String getDbName() {
		return dbName;
	}



	public void setDbName(String dbName) {
		this.dbName = dbName;
	}



	public String getMeasurement() {
		return measurement;
	}



	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}



	public String getFieldName() {
		return fieldName;
	}



	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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
		return "BlackListDO [id=" + id + ", appId=" + appId + ", dbName=" + dbName + ", measurement=" + measurement
				+ ", fieldName=" + fieldName + ", creatTime=" + creatTime + ", modifyTime=" + modifyTime + "]";
	}


	
}
