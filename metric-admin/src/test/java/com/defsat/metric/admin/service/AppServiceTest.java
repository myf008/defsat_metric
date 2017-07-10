package com.defsat.metric.admin.service;


import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.defsat.metric.admin.dao.daoobject.AppDO;
import com.defsat.metric.admin.service.AppService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class AppServiceTest {
	
	@Autowired
    private AppService service;

    
	@Test
    public void testAddApp() throws Exception {
    	AppDO appDo = new AppDO("to.money","toa-money","zhangsan","zhangsan@163.com.cn","1234546");
    	service.createApp(appDo);
    	AppDO app = service.getAppById(appDo.getAppId());
    	log.debug(app.toString());
    	assertThat(app.getDescription(), equalTo("to-money"));
    }
	
    @Test
    public void testGetAppById() throws Exception {
    	String appId = "to.money";
        AppDO appDo = service.getAppById(appId);
        log.debug(appDo.toString());
        assertThat(appDo.getAppId(), equalTo(appId));
    }
    
    @Test
    public void testUpdateApp() throws Exception {
    	String appId = "to.money";
    	String desc = "TOA MONEY";
    	AppDO appDo = service.getAppById(appId);
    	appDo.setDescription(desc);
    	service.updateByAppId(appDo);
    	appDo = service.getAppById(appId);
    	log.debug(appDo.toString());
    	assertThat(appDo.getDescription(), equalTo(desc));
    }
    
    @Test
    public void testDelApp() throws Exception {
    	service.deleteApp("to.money");
    	AppDO appDo = service.getAppById("to.money");
    	log.debug(appDo==null?"null":appDo.toString());
    	assertThat(appDo, equalTo(null));
    }
    
   
    
}
