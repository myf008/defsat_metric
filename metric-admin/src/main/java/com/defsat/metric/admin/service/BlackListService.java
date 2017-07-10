package com.defsat.metric.admin.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.defsat.metric.admin.dao.daointerface.BlackListDAO;
import com.defsat.metric.admin.dao.daoobject.BlackListDO;
import com.defsat.metric.util.StringUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

@Slf4j
@Service("blackListService")
public class BlackListService {

	@Autowired
	BlackListDAO blackListDAO;
	
	public List<String> getBlackList(String appId){
		List<String> list = Lists.newArrayList();
		List<BlackListDO>  BlackList = getBlackListDo(appId);
		for(BlackListDO blackDo : BlackList){
			String dbName = blackDo.getDbName();
			String measurement = blackDo.getMeasurement();
			String field = blackDo.getFieldName();
			
			Iterable<String> fields = Splitter.on(",").omitEmptyStrings().split(field);
			for(String f : fields){
				String blackStr = Joiner.on(StringUtils.BLACK_LIST_SEPRATOR).skipNulls().join(appId, dbName, measurement, f.trim());
				list.add(blackStr);
			}
		}
		return list;
	}
	
	public List<BlackListDO> getBlackListDo(String appId){
		List<BlackListDO>  fieldBlackList = blackListDAO.getBlackList(appId);
		return fieldBlackList;
	}
	
	public List<String> getAllBlackList(){
		List<String> list = Lists.newArrayList();
		List<BlackListDO>  blackList = getAllBlackListDo();
		for(BlackListDO blackDo : blackList){
			String appId = blackDo.getAppId();
			String dbName = blackDo.getDbName();
			String measurement = blackDo.getFieldName();
			String field = blackDo.getFieldName();
			
			String blackStr = Joiner.on(StringUtils.BLACK_LIST_SEPRATOR).join(appId, dbName, measurement, field);
			list.add(blackStr);
		}
		return list;
	}
	
	public List<BlackListDO> getAllBlackListDo(){
		List<BlackListDO>  BlackList = blackListDAO.getAllBlackList();
		return BlackList;
	}
	
	@Transactional
	public void createBlackList(BlackListDO blackListDO){
		log.debug("create blackList : {}", blackListDO.toString());
		blackListDAO.insert(blackListDO);
	}

	@Transactional
	public void deleteBlackList(String appId){
		log.debug("delete blackList, appId : {}", appId);
		blackListDAO.delete(appId);
	}
	
	@Transactional
	public void delOneBlackList(BlackListDO blackListDO){
		log.debug("delete blackList, blackListDO : {}", blackListDO.toString());
		blackListDAO.delOne(blackListDO);
	}

	@Transactional
	public void updateBlackList(BlackListDO blackListDO){
		log.debug("update blackList with appId : {}", blackListDO.getAppId());
		blackListDAO.update(blackListDO);
	}
}
