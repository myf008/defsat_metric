package com.defsat.metric.admin.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.defsat.metric.admin.dao.daoobject.BlackListDO;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class BlackListServiceTest {

	@Autowired
	BlackListService blackListService;
	
	@Test
    public void testAddBlackList() throws Exception {
    	BlackListDO blackListDO = new BlackListDO("to.money","stg","stg","value33");
    	blackListService.createBlackList(blackListDO);
    	BlackListDO black = blackListService.getBlackListDo(blackListDO.getAppId()).get(0);
    	log.debug(black.toString());
    	assertThat(black.getFieldName(), equalTo("value33"));
    }
	
	@Test
	public void testGetBlackList(){
		String appId = "to.money";
		List<String> blackList = blackListService.getBlackList(appId);
		log.debug(blackList.toString());
		assertThat(blackList.get(0), equalTo("to.money&stg&stg&value11"));
	}
	
    
	@Test
    public void testUpdateBlackList() throws Exception {
    	String appId = "to.money";
    	String field = "vvvv";
    	BlackListDO blackListDO = blackListService.getBlackListDo(appId).get(0);
    	blackListDO.setFieldName(field);
    	blackListService.updateBlackList(blackListDO);
    	blackListDO = blackListService.getBlackListDo(appId).get(0);
    	log.debug(blackListDO.toString());
    	assertThat(blackListDO.getFieldName(), equalTo(field));
    }
	
    @Test
    public void testDelBlackList() throws Exception {
    	blackListService.deleteBlackList("to.money");
    	List<BlackListDO> blackListDO = blackListService.getBlackListDo("to.money");
    	log.debug(blackListDO==null?"null":blackListDO.toString());
    	assertThat(blackListDO.isEmpty(), equalTo(true));
    }
    
    
	
	
	
}
