package com.defsat.metric.metric;

import java.util.Map;
import com.google.common.collect.Maps;


public class MetricData {
	private String name;
	
	private Map<String,String> tags = Maps.newHashMap();
	
	private Map<String,Object> fields = Maps.newHashMap();
	
	private long time;
	
	
	public MetricData(){
		this.tags = Maps.newHashMap();
		this.fields = Maps.newHashMap();
		this.time = System.currentTimeMillis();
	}
	
	public MetricData(String name,String metric,Map<String,String> tags,long time,Object value){
		this.name = name;
		if(tags != null){
			this.tags = tags;
		}
		fields.put(metric, value);
		this.time = time;
	}
		
	public MetricData(String name, Map<String,String> tags, Map<String,Object> fields, long time){
		this.name = name;
		if(tags != null){
			this.tags = tags;
		}
		if(fields != null){
			this.fields = fields;
		}
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getTags() {
		return tags;
	}

	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	
}
