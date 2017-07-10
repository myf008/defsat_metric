# defsat_metric
a metric platform based on influxdb in java


使用指南： 
1.在classpath下添加metric.properties:    
（1）若是直连influxdb，则配置如：    
  metric.serverAddr=192.168.242.141:8086   
  metric.storagePolicy=influxdb   
  metric.dbname=stg   
  metric.username=root   
  metric.password=root  
  metric.retention=default  
  
 (2)若通过kafka中转，则配置如：  
 metric.appId=aaa  
 metric.kafka.bootstrap.servers=192.168.242.136:9092  
 注：influxdb等配置在metric-admin中配置  
 
 2.调用方法：  
 IMetricAgent agent = AgentFactory.getAgent();  
 Map<String,String> tags = Maps.newHashMap();  
 Map<String,Object> fields = Maps.newHashMap();  
 tags.put("name", "stg");  
 fields.put("value", j);  
 fields.put("value11", j);  
 this.agent.log("stg",tags, fields);  
 
 3.influxdb管理界面：influxdb server ：8083  
 数据展示：grafana server ：3000
