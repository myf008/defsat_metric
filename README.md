# defsat_metric
a metric platform based on influxdb in java


User Guide： 
1.add the file metric.properties under classpath:    
（1）if direct connect to influxdb，like this：    
  metric.serverAddr=192.168.242.141:8086   
  metric.storagePolicy=influxdb   
  metric.dbname=stg   
  metric.username=root   
  metric.password=root  
  metric.retention=default  
  
 (2)if send metric data by kafka，like this：  
 metric.appId=aaa  
 metric.kafka.bootstrap.servers=192.168.242.136:9092  
 note：configure influxdb in metric-admin platform  
 
 2.Get a agent and call method：  
 IMetricAgent agent = AgentFactory.getAgent();  
 Map<String,String> tags = Maps.newHashMap();  
 Map<String,Object> fields = Maps.newHashMap();  
 tags.put("name", "stg");  
 fields.put("value", 12);  
 fields.put("value11", 34);  
 this.agent.log("stg",tags, fields);  
 
 3.influxdb manager interface：influxdb server ：8083  
 show metric data：grafana server ：3000
