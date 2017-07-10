package com.defsat.metric.admin.filter;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.defsat.metric.admin.service.AppInfluxdbRelServiceTest;
import com.defsat.metric.admin.service.BlackListService;
import com.defsat.metric.util.SleepUtils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class BlackListTest {
	
	private LoadingCache <String, List<String>> cache;

	@Autowired
	BlackListService blackListService;
	
	
	@PostConstruct
	public void init(){
		this.cache = CacheBuilder.newBuilder()
				.maximumSize(1000)
				.refreshAfterWrite(1, TimeUnit.MINUTES)
				.build(new CacheLoader<String,List<String>>(){

					@Override
					public List<String> load(String key) throws Exception {
						return blackListService.getBlackList(key);
					}
				});
	}
	
	@Test
	public void testCacheRefresh(){
		String appId = "aaa.bbb";
		List<String> dataList = null;
		while(true){
			try {
				dataList = cache.get(appId);
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			log.debug("appId:{}, blackList:{}", appId,dataList.get(0));
			SleepUtils.sleep(10000);
		}
		
	}
	
}
