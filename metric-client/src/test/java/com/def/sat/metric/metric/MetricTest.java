package com.def.sat.metric.metric;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.defsat.metric.AgentFactory;
import com.defsat.metric.IMetricAgent;
import com.defsat.metric.config.MetricConfig;
import com.defsat.metric.storage.StorageType;
import com.defsat.metric.util.SleepUtils;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;

@Slf4j
@RunWith(Parameterized.class)
public class MetricTest {
	private MetricConfig config;
	private IMetricAgent agent;
	private int threadNum;
	
	
	@Parameters
	public static Collection data(){
		return Arrays.asList(new Object[][]{
			 { "192.168.48.129:8086",// addr
				"root",// name
				"root", // password
				"stg",//dbname
				(int)Math.pow(2, 14),//bufferSize
				10,//workerSize
				20,//workerBufSize
				StorageType.INFLUXDB,//sendPolicy
				"default",//retention
				"ONE", //consistent
				1,//threadNum
				"aaa",//appId
				"192.168.48.129:9092",//kafka Servers
				"org.apache.kafka.common.serialization.StringSerializer",//key serialier
				"com.defsat.metric.util.MetricMessageSerialier",//value serialier
				"0",//acks
				1,//retries
				"metric"//topic
			  }
		});
	}
	
	public MetricTest(String serverAddr, String username, String password, String dbName, int bufferSize,
			int workerSize, int workerBufSize, StorageType sendPolicy, String retention, String consistent,
			int threadNum, String appId, String kafkaServers, String keySerializer, String valueSerializer,
			String acks, int retries, String topic) {

		this.config = new MetricConfig(serverAddr, username, password, dbName, bufferSize, workerSize, workerBufSize,
				sendPolicy, retention, consistent, appId, kafkaServers, keySerializer, valueSerializer, acks, retries,
				topic);

		this.agent = AgentFactory.getAgent(config);

		this.threadNum = threadNum;

	}
	
	
	@Test
	public void influxdbTest(){
		
		
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i=0; i<this.threadNum; i++){
			service.execute(new Producer(agent));
		}
		 
		while(true){
			SleepUtils.sleep(3000);
		}
	}
	
	
	class Producer implements Runnable{
		private IMetricAgent agent;
		
		public Producer(IMetricAgent agent){
			this.agent = agent;
		}
		
		@Override
		public void run() {
//			while(true){
				Stopwatch watch = Stopwatch.createStarted();
				for(int j=0; j<200; j++){
					Map<String,String> tags = Maps.newHashMap();
					Map<String,Object> fields = Maps.newHashMap();
					tags.put("name", "stg");
					fields.put("value", j);
					fields.put("value11", j);
					this.agent.log("stg",tags, fields);
					SleepUtils.sleep(100);
				}
				System.out.println("2W total time： " + watch);
//			}
		}
		
	}
}
