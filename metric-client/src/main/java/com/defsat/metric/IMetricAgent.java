package com.defsat.metric;

import java.util.Map;

public interface IMetricAgent {

	void log(String name,String metric,Map<String,String> tags,int value);
	
	void log(String name,String metric,Map<String,String> tags,long time,int value);
	
	void log(String name,String metric,Map<String,String> tags, String value);
	
	void log(String name,String metric,Map<String,String> tags,long time, String value);
	
	void log(String name,String metric,Map<String,String> tags,long value);
	
	void log(String name,String metric,Map<String,String> tags,long time,long value);
	
	void log(String name,String metric,Map<String,String> tags,double value);
	
	void log(String name,String metric,Map<String,String> tags,long time,double value);
	
	void log(String name,String metric,Map<String,String> tags,float value);
	
	void log(String name,String metric,Map<String,String> tags,long time,float value);
	
	void log(String name, Map<String,String> tags,Map<String, Object> fields);
	
	void log(String name, Map<String,String> tags,Map<String, Object> fields, long time);
	
}
