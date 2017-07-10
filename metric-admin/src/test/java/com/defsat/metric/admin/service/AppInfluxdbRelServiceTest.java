package com.defsat.metric.admin.service;


import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.defsat.metric.admin.dao.daoobject.AppInfluxRelDO;
import com.defsat.metric.admin.dao.daoobject.InfluxdbDO;
import com.defsat.metric.admin.service.AppInfluxRelService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class AppInfluxdbRelServiceTest {
	
	@Autowired
    private AppInfluxRelService service;

    
	@Test
    public void testAddRelation() throws Exception {
    	AppInfluxRelDO relationDo = new AppInfluxRelDO("to.xx","c1","test","default");
    	service.createRelation(relationDo);
    	relationDo = service.getRelation(relationDo.getAppId());
    	log.debug(relationDo.toString());
    	assertThat(relationDo.getInfluxdbId(), equalTo("c4"));
    }
	
    @Test
    public void testGetRelation() throws Exception {
    	String appId = "to.xx";
        AppInfluxRelDO relationDo = service.getRelation(appId);
        log.debug(relationDo.toString());
        assertThat(relationDo.getAppId(), equalTo(appId));
    }
    
    
    @Test
    public void testUpdateRelation() throws Exception {
    	String appId = "to.xx";
    	String influxId = "c2";
    	
    	AppInfluxRelDO relationDo = service.getRelation(appId);
    	relationDo.setInfluxdbId(influxId);
    	service.updateRelation(relationDo);
    	relationDo = service.getRelation(appId);
    	log.debug(relationDo.toString());
    	assertThat(relationDo.getInfluxdbId(), equalTo(influxId));
    }
    
    @Test
    public void testDelRelation() throws Exception {
    	String appId = "to.xx";
    	service.deleteRelation(appId);
    	AppInfluxRelDO relationDo = service.getRelation(appId);
    	log.debug(relationDo==null?"null":relationDo.toString());
    	assertThat(relationDo, equalTo(null));
    }
    
   
    
}
