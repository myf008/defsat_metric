package com.defsat.metric.metric;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class MetricMessage {
	private MetricHeader metricHeader;
	private List<MetricData> metricDataList;
	
	
	public MetricMessage() {
	}

	public MetricMessage(MetricHeader metricHeader, List<MetricData> metricDataList) {
		this.metricHeader = metricHeader;
		this.metricDataList = metricDataList;
	}
	

	public MetricHeader getMetricHeader() {
		return metricHeader;
	}

	public void setMetricHeader(MetricHeader metricHeader) {
		this.metricHeader = metricHeader;
	}


	public List<MetricData> getMetricDataList() {
		return metricDataList;
	}

	public void setMetricDataList(List<MetricData> metricDataList) {
		this.metricDataList = metricDataList;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
