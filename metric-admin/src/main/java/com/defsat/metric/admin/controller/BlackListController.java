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
import com.defsat.metric.admin.dao.daoobject.BlackListDO;
import com.defsat.metric.admin.service.BlackListService;


@Slf4j
@RestController
@RequestMapping(value = "/blacklist")
public class BlackListController {

	@Autowired
	private BlackListService blackListService;
	
	@RequestMapping(value = "/{appId:.*}", method = RequestMethod.GET)
	public Result<List<BlackListDO>> get(@PathVariable String appId) {
		List<BlackListDO> blackList = blackListService.getBlackListDo(appId);
		Result<List<BlackListDO>> result = new Result<List<BlackListDO>>();
		boolean success = blackList.isEmpty() ? false : true;
		String message = blackList.isEmpty() ? "error: get blackList failed!" : "OK";
		result.setSuccess(success);
		result.setResult(blackList);
		result.setMessages(message);
		return result;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Result<List<BlackListDO>> getAll() {
		Result<List<BlackListDO>> result = new Result<List<BlackListDO>>();
		try{
			List<BlackListDO> blackList = blackListService.getAllBlackListDo();
			result.setSuccess(true);
			result.setResult(blackList);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("get all blackLists failed!", t.getMessage());
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/delete/{appId:.*}", method = RequestMethod.POST)
	public Result<String> delete(@PathVariable String appId) {
		Result<String> result = new Result<String>();
		
		try{
			blackListService.deleteBlackList(appId);
			result.setSuccess(true);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("delete blackList failed! with appId : " + appId, t);
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value = "/delOne", method = RequestMethod.POST)
	public Result<String> deleteOne(@RequestParam (value="blacklist", defaultValue="") String blackListStr) {
		Result<String> result = new Result<String>();
		
		try{
			JSONObject jsonObj = JSONObject.parseObject(blackListStr);
			String appId = jsonObj.getString("appId");
			String dbName = jsonObj.getString("dbName");
			String measurement = jsonObj.getString("measurement");
			String fieldName = jsonObj.getString("fieldName");
			final BlackListDO blackListDo = new BlackListDO(appId,dbName,measurement,fieldName);
			
			blackListService.delOneBlackList(blackListDo);
			result.setSuccess(true);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("delete blackList failed! blackListDO : " + blackListStr, t);
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result<String> createBlackList(@RequestParam (value="blacklist", defaultValue="") String blackListStr) {
		Result<String> result = new Result<String>();
		
		try{
			JSONObject jsonObj = JSONObject.parseObject(blackListStr);
			String appId = jsonObj.getString("appId");
			String dbName = jsonObj.getString("dbName");
			String measurement = jsonObj.getString("measurement");
			String fieldName = jsonObj.getString("fieldName");
			if (Strings.isNullOrEmpty(appId) || Strings.isNullOrEmpty(dbName) || Strings.isNullOrEmpty(measurement)
					|| Strings.isNullOrEmpty(fieldName)) {
				result.setSuccess(false);
				result.setMessages("appId, dbName, measurement, fieldName cannot be null!");
				return result;
			}
			
			final BlackListDO blackListDo = new BlackListDO(appId,dbName,measurement,fieldName);
			
			blackListService.createBlackList(blackListDo);
			result.setSuccess(true);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("add blackList failed!", t);
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> updateBlackList(@RequestParam (value="blacklist", defaultValue="") String blackListStr) {
		Result<String> result = new Result<String>();
		
		try{
			JSONObject jsonObj = JSONObject.parseObject(blackListStr);
			String appId = jsonObj.getString("appId");
			String dbName = jsonObj.getString("dbName");
			String measurement = jsonObj.getString("measurement");
			String fieldName = jsonObj.getString("fieldName");
			if (Strings.isNullOrEmpty(appId) || Strings.isNullOrEmpty(dbName) || Strings.isNullOrEmpty(measurement)
					|| Strings.isNullOrEmpty(fieldName)) {
				result.setSuccess(false);
				result.setMessages("appId, dbName, measurement, fieldName cannot be null!");
				return result;
			}
			final BlackListDO blackListDo = new BlackListDO(appId,dbName,measurement,fieldName);
			
			blackListService.updateBlackList(blackListDo);
			result.setSuccess(true);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("update blackList failed!", t);
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	
}
