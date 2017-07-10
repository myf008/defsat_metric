package com.defsat.metric.admin.dao.daoobject;

import java.io.Serializable;
import java.util.Date;


public class InfluxdbDO implements Serializable{

	/**  */
	private static final long serialVersionUID = -4023086857464276025L;

	/** 自增主键 */
	private Integer id;

	/** influxdb Id(ip : port) */
	private String influxdbId;
	
	/** 用户名*/
	private String userName;
	
	/** 密码*/
	private String password;
	
	/** 创建时间 */
	private Date creatTime;

	/** 修改时间 */
	private Date modifyTime;


	public InfluxdbDO(){
		
	}

	public InfluxdbDO(String influxdbId, String userName, String password) {
		this.influxdbId = influxdbId;
		this.userName = userName;
		this.password = password;
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getInfluxdbId() {
		return influxdbId;
	}


	public void setInfluxdbId(String influxdbId) {
		this.influxdbId = influxdbId;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
		return "InfluxdbDO [id=" + id + ", influxdbId=" + influxdbId + ", userName="
				+ userName + ", password=" + password + ", creatTime=" + creatTime + ", modifyTime=" + modifyTime + "]";
	}

	
	
}
