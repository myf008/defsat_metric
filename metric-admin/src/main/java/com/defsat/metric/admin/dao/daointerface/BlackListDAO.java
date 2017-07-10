package com.defsat.metric.admin.dao.daointerface;

import java.util.List;

import com.defsat.metric.admin.dao.daoobject.BlackListDO;

public interface BlackListDAO {

	List<BlackListDO> getBlackList(String appId);
	
	List<BlackListDO> getAllBlackList();
	
	void insert(BlackListDO fieldBlackListDO);

	void delete(String appId);

	void delOne(BlackListDO fieldBlackListDO);
	
	void update(BlackListDO fieldBlackListDO);
}
