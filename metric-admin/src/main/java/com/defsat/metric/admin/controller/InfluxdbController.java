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
import com.defsat.metric.admin.dao.daoobject.InfluxdbDO;
import com.defsat.metric.admin.service.InfluxdbService;


@Slf4j
@RestController
@RequestMapping(value = "/influxdb")
public class InfluxdbController {

	@Autowired
	private InfluxdbService influxdbService;

	@RequestMapping(value = "/{influxdbId:.*}", method = RequestMethod.GET)
	public Result<InfluxdbDO> getInfluxdbById(@PathVariable String influxdbId) {
		InfluxdbDO influxdbDO = influxdbService.getInfluxdb(influxdbId);
		Result<InfluxdbDO> result = new Result<InfluxdbDO>();
		boolean success = influxdbDO==null ? false : true;
		String message = influxdbDO==null ? "error: get influxdb failed!" : "OK";
		result.setSuccess(success);
		result.setResult(influxdbDO);
		result.setMessages(message);
		return result;
	}
	
	
	@RequestMapping(value = "/influxdbList", method = RequestMethod.GET)
	public Result<List<InfluxdbDO>> influxdbList() {
		Result<List<InfluxdbDO>> result = new Result<List<InfluxdbDO>>();
		try{
			List<InfluxdbDO> influxdbDOList = influxdbService.getAllInfluxdb();
			result.setSuccess(true);
			result.setMessages("OK");
			result.setResult(influxdbDOList);
		}catch (Throwable t) {
			log.error("error: get all influxdb failed!", t.getMessage());
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}	
		
		return result;
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<String> deleteInfluxdb(@RequestParam(value = "influxdbId", defaultValue = "") String influxdbId) {
		Result<String> result = new Result<String>();
		
		try{
			influxdbService.deleteInfluxdb(influxdbId);
			result.setSuccess(true);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("delete influxdb failed!", t);
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result<String> createInfluxdb(@RequestParam(value = "influxdb", defaultValue = "") String influxdbStr) {
		Result<String> result = new Result<String>();
		
		try{
			JSONObject jsonObj = JSONObject.parseObject(influxdbStr);
			String influxdbId = jsonObj.getString("influxdbId");
			String userName = jsonObj.getString("userName");
			String password = jsonObj.getString("password");
			if (Strings.isNullOrEmpty(influxdbId) || Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(password)) {
				result.setSuccess(false);
				result.setMessages("Address, UserName, Password cannot be null!");
				return result;
			}
			InfluxdbDO influxdbDo = new InfluxdbDO(influxdbId,userName,password);
			
			influxdbService.createInfluxdb(influxdbDo);
			result.setSuccess(true);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("add influxdb failed!", t);
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> updateByAppId(@RequestParam(value = "influxdb", defaultValue = "") String influxdbStr) {
		Result<String> result = new Result<String>();
		
		try{
			JSONObject jsonObj = JSONObject.parseObject(influxdbStr);
			String influxdbId = jsonObj.getString("influxdbId");
			String userName = jsonObj.getString("userName");
			String password = jsonObj.getString("password");
			if (Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(password)) {
				result.setSuccess(false);
				result.setMessages("IP, UserName, Password cannot be null!");
				return result;
			}
			InfluxdbDO influxdbDo = new InfluxdbDO(influxdbId,userName,password);
			
			influxdbService.updateByInfluxdbId(influxdbDo);
			result.setSuccess(true);
			result.setMessages("OK");
		}catch (Throwable t) {
			log.error("update influxdb by influxdbId failed!", t);
			result.setSuccess(false);
			result.setMessages(t.getMessage());
		}
		
		return result;
	}
	
	
}
