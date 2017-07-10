package com.defsat.metric.admin.dao.daoobject;

import java.io.Serializable;
import java.util.Date;


public class AppDO implements Serializable{

	/**  */
	private static final long serialVersionUID = -5287359116569168629L;

	/** 自增主键 */
	private Integer id;

	/** app id */
	private String appId;
	
	/** 描述 */
	private String description;
	
	/** 负责人 */
	private String owner;
	
	/** 邮件 */
	private String mail;
	
	/** 电话 */
	private String telephone;
	
	/** 创建时间 */
	private Date creatTime;

	/** 修改时间 */
	private Date modifyTime;
	

	public AppDO(){
		
	}

	public AppDO(String appId, String description, String owner, String mail, String telephone) {
		this(appId, description, owner, mail, telephone,null,null);
	}
	

	public AppDO(String appId, String description, String owner, String mail, String telephone,
			Date creatTime, Date modifyTime) {
		this.appId = appId;
		this.description = description;
		this.owner = owner;
		this.mail = mail;
		this.telephone = telephone;
		this.creatTime = creatTime;
		this.modifyTime = modifyTime;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
		return "AppDO [id=" + id + ", appId=" + appId + ", description=" + description + ", owner=" + owner + ", mail="
				+ mail + ", telephone=" + telephone + ", creatTime=" + creatTime + ", modifyTime=" + modifyTime + "]";
	}
	
	
}
