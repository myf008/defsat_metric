package com.defsat.metric.admin.filter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.defsat.metric.admin.config.ConfigConstant;
import com.defsat.metric.admin.dao.daoobject.AppInfluxRelDO;
import com.defsat.metric.admin.service.AppInfluxRelService;
import com.defsat.metric.admin.service.BlackListService;
import com.defsat.metric.filter.IMetricFilter;
import com.defsat.metric.metric.MetricData;
import com.defsat.metric.metric.MetricMessage;
import com.defsat.metric.util.StringUtils;
import com.google.common.base.Joiner;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;

@Component
@Slf4j
public class AdminMetricFilter implements IMetricFilter {

	private LoadingCache <String, List<String>> blackListCache;
	private Map<String,String> appDbMap;
	
	@Autowired
	BlackListService blackListService;
	
	@Autowired
	AppInfluxRelService relationService;
	
	
	public AdminMetricFilter(){
		initBlackListCache();
		this.appDbMap = Maps.newHashMap();
	}
	
	private void initBlackListCache(){
		this.blackListCache = CacheBuilder.newBuilder()
				.maximumSize(ConfigConstant.BLACKLIST_MAX_NUM)
				.refreshAfterWrite(ConfigConstant.BLACKLIST_REFRESH_INTERVAL, TimeUnit.MINUTES)
				.build(new CacheLoader<String,List<String>>(){

					@Override
					public List<String> load(String key) throws Exception {
						return blackListService.getBlackList(key);
					}
				});
	}
	
	private void updateAppDbMap(String appId){
		AppInfluxRelDO relationDo = this.relationService.getRelation(appId);
		String dbName = relationDo.getDbName();
		this.appDbMap.put(appId, dbName);
	}
	
	@Override
	public MetricMessage doFilterData(List<MetricData> dataList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MetricMessage> doFilterMessage(List<MetricMessage> msgList) {
		for(MetricMessage msg : msgList){
			filterOneMsg(msg);
		}
		return msgList;
	}

	public void filterOneMsg(MetricMessage msg) {
		String appId = msg.getMetricHeader().getAppId();
		List<MetricData> dataList = msg.getMetricDataList();
		filteTags(dataList);
		filteFields(dataList, appId);
		
		msg.setMetricDataList(dataList);
	}
	
	

	
	private void filteTags(List<MetricData> dataList){
		for(MetricData data : dataList){
			Map<String,String> tags = data.getTags();
			Set<Entry<String,String>> entrySet = tags.entrySet();
			for(Entry<String,String> entry : entrySet){
				String key = entry.getKey();
				String value = entry.getValue();
				if(value.length() > ConfigConstant.TAG_MAX_LENGTH){
					value = value.substring(0, ConfigConstant.TAG_MAX_LENGTH);
					tags.put(key, value);
				}
			}
		}
	}
	
	private void filteFields(List<MetricData> dataList,String appId){
		if(!appDbMap.containsKey(appId)){
			updateAppDbMap(appId);
		}
		for(MetricData data : dataList){
			String measurement = data.getName();
			Map<String,Object> fields = data.getFields();
			Iterator<String> it = fields.keySet().iterator(); 
			while(it.hasNext())
			{ 
		        String key = it.next(); 
		        String keyStr = Joiner.on(StringUtils.BLACK_LIST_SEPRATOR).join(appId, appDbMap.get(appId), measurement, key);
				List<String> blackList;
				try {
					blackList = this.blackListCache.get(appId);
					if(blackList.contains(keyStr)){
						it.remove();
					}
				} catch (Exception e) {
					log.error("get blackList failed for appId : {}",appId,e.getMessage());
				}
		    }
		}
	}

}
