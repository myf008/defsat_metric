package com.defsat.metric.admin.controller;


import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.defsat.metirc.admin.common.Result;
import com.defsat.metric.admin.dao.daoobject.AppDO;
import com.defsat.metric.admin.service.AppService;


@Slf4j
@RestController
@RequestMapping(value = "/app")
public class AppController {

	@Autowired
	private AppService appService;
	
	@RequestMapping(value = "/{appId:.*}", method = RequestMethod.GET)
	public Result<AppDO> getAppById(@PathVariable String appId) {
		AppDO appDO = appService.getAppById(appId);
		Result<AppDO> result = new Result<AppDO>();
		boolean success = appDO==null ? false : true;
		String message = appDO==null ? "error: get app failed!" : "OK";
		result.setSuccess(success);
		result.setResult(appDO);
		result.setMessages(message);
		return result;
	}
	
	@RequestMapping(value = "/appList", method = RequestMethod.GET)
	public Result<List<AppDO>> appList() {
		Result<List<AppDO>> result = new Result<List<AppDO>>();
		try{
			List<AppDO> appDOList = appService.getAllApps();
			result.setSuccess(true);
			result.setResult(appDOList);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("get all apps failed!", t.getMessage());
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<String> deleteApp(@RequestParam(value = "appId", defaultValue = "") String appId) {
		Result<String> result = new Result<String>();
		
		try{
			appId = new String(appId.getBytes("ISO-8859-1"), "utf-8");
			appService.deleteApp(appId);
			result.setSuccess(true);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("delete app failed!", t);
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result<String> createApp(@RequestParam(value = "app", defaultValue = "") String appStr) {
		Result<String> result = new Result<String>();
		
		try{
			appStr = new String(appStr.getBytes("ISO-8859-1"), "utf-8");
			JSONObject jsonObj = JSONObject.parseObject(appStr);
			String appId = jsonObj.getString("appId");
			if(Strings.isNullOrEmpty(appId)){
				result.setSuccess(false);
				result.setMessages("appId cannot be null!");
				return result;
			}
			String desc = jsonObj.getString("description");
			String owner = jsonObj.getString("owner");
			String mail = jsonObj.getString("mail");
			String telephone = jsonObj.getString("telephone");
			AppDO appDo = new AppDO(appId,desc,owner,mail,telephone);
			
			appService.createApp(appDo);
			result.setSuccess(true);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("add app failed!", t);
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> updateByAppId(@RequestParam(value = "app", defaultValue = "") String appStr) {
		Result<String> result = new Result<String>();
		
		try{
			appStr = new String(appStr.getBytes("ISO-8859-1"), "utf-8");
			JSONObject jsonObj = JSONObject.parseObject(appStr);
			String appId = jsonObj.getString("appId");
			String desc = jsonObj.getString("description");
			String owner = jsonObj.getString("owner");
			String mail = jsonObj.getString("mail");
			String telephone = jsonObj.getString("telephone");
			AppDO appDo = new AppDO(appId,desc,owner,mail,telephone);
			
			appService.updateByAppId(appDo);
			result.setSuccess(true);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("update app by appId failed.", t);
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	
}
