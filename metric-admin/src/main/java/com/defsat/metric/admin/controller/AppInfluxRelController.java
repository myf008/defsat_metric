package com.defsat.metric.admin.controller;


import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.defsat.metirc.admin.common.Result;
import com.defsat.metric.admin.dao.daoobject.AppInfluxRelDO;
import com.defsat.metric.admin.service.AppInfluxRelService;


@Slf4j
@RestController
@RequestMapping(value = "/relation")
public class AppInfluxRelController {

	@Autowired
	private AppInfluxRelService relationService;

	@RequestMapping(value = "/{appId:.*}", method = RequestMethod.GET)
	public Result<AppInfluxRelDO> getRelation(@PathVariable String appId) {
		AppInfluxRelDO appInfluxRelDO = relationService.getRelation(appId);
		Result<AppInfluxRelDO> result = new Result<AppInfluxRelDO>();
		boolean success = appInfluxRelDO==null ? false : true;
		String message = appInfluxRelDO==null ? "error: get appInfluxRel failed!" : "OK";
		result.setSuccess(success);
		result.setResult(appInfluxRelDO);
		result.setMessages(message);
		return result;
	}
	
	@RequestMapping(value = "/relationList", method = RequestMethod.GET)
	public Result<List<AppInfluxRelDO>> relationList() {
		Result<List<AppInfluxRelDO>> result = new Result<List<AppInfluxRelDO>>();
		try{
			List<AppInfluxRelDO> appInfluxRelDOList = relationService.getAllRelations();
			result.setSuccess(true);
			result.setResult(appInfluxRelDOList);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("get all appInfluxRels failed!", t.getMessage());
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<String> deleteApp(@RequestParam(value = "appId", defaultValue = "") String appId) {
		Result<String> result = new Result<String>();
		
		try{
			relationService.deleteRelation(appId);
			result.setSuccess(true);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("delete appInfluxRel failed!", t);
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result<String> createRelation(@RequestParam (value = "relation", defaultValue="") String relationStr) {
		Result<String> result = new Result<String>();
		
		try{
			JSONObject object = JSON.parseObject(relationStr);
			String appId = object.getString("appId");
			String influxdbId = object.getString("influxdbId");
			String dbName = object.getString("dbName");
			String retention = object.getString("retention");
			if (Strings.isNullOrEmpty(appId) || Strings.isNullOrEmpty(influxdbId) || Strings.isNullOrEmpty(dbName)
					|| Strings.isNullOrEmpty(retention)) {
				result.setSuccess(false);
				result.setMessages("appId, influxdbId, dbName, retention cannot be null!");
				return result;
			}
			AppInfluxRelDO relationDo = new AppInfluxRelDO(appId, influxdbId, dbName, retention);
			
			relationService.createRelation(relationDo);
			result.setSuccess(true);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("add appInfluxRel failed!", t);
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> updateRelation(@RequestParam (value = "relation", defaultValue="") String relationStr) {
		Result<String> result = new Result<String>();
		
		try{
			JSONObject object = JSON.parseObject(relationStr);
			String appId = object.getString("appId");
			String influxdbId = object.getString("influxdbId");
			String dbName = object.getString("dbName");
			String retention = object.getString("retention");
			if (Strings.isNullOrEmpty(appId) || Strings.isNullOrEmpty(influxdbId) || Strings.isNullOrEmpty(dbName)
					|| Strings.isNullOrEmpty(retention)) {
				result.setSuccess(false);
				result.setMessages("appId, influxdbId, dbName, retention cannot be null!");
				return result;
			}
			AppInfluxRelDO relationDo = new AppInfluxRelDO(appId, influxdbId, dbName, retention);
			
			relationService.updateRelation(relationDo);
			result.setSuccess(true);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("update appInfluxRel failed!", t);
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	
}
