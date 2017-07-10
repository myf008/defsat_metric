package com.defsat.metric.admin.service;


import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.defsat.metric.admin.dao.daoobject.AppDO;
import com.defsat.metric.admin.dao.daoobject.InfluxdbDO;
import com.defsat.metric.admin.service.InfluxdbService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class InfluxdbServiceTest {
	
	@Autowired
    private InfluxdbService service;

	@Test
    public void testAddInfluxdb() throws Exception {
    	InfluxdbDO influxDo = new InfluxdbDO("100.200.16.18:100","root","root");
    	service.createInfluxdb(influxDo);
    	InfluxdbDO influx = service.getInfluxdb(influxDo.getInfluxdbId());
    	log.debug(influx.toString());
    	assertThat(influx.getInfluxdbId(), equalTo("c2"));
    }
	
	
    @Test
    public void testGetInfluxdbById() throws Exception {
    	String influxId = "100.200.16.18:100";
        InfluxdbDO influxDo = service.getInfluxdb(influxId);
        log.debug(influxDo.toString());
        assertThat(influxDo.getInfluxdbId(), equalTo(influxId));
    }
    
    
    @Test
    public void testUpdateInfluxdb() throws Exception {
    	String influxId = "100.200.16.18:100";
    	InfluxdbDO influxDo = service.getInfluxdb(influxId);
    	service.updateByInfluxdbId(influxDo);
    	influxDo = service.getInfluxdb(influxId);
    	log.debug(influxDo.toString());
    	assertThat(influxDo.getInfluxdbId(), equalTo(influxId));
    }
    
    @Test
    public void testDelInfluxdb() throws Exception {
    	String influxId = "100.200.16.18:100";
    	service.deleteInfluxdb(influxId);
    	InfluxdbDO influxDo = service.getInfluxdb(influxId);
    	log.debug(influxDo==null?"null":influxDo.toString());
    	assertThat(influxDo, equalTo(null));
    }
    
   
    
}
