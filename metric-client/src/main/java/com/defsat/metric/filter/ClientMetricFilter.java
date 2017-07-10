package com.defsat.metric.filter;

import java.util.List;

import com.defsat.metric.config.MetricConfig;
import com.defsat.metric.metric.MetricData;
import com.defsat.metric.metric.MetricHeader;
import com.defsat.metric.metric.MetricMessage;

public class ClientMetricFilter implements IMetricFilter {

	private String appId;
	
	
	public ClientMetricFilter(MetricConfig config) {
		this.appId = config.getAppId();
	}


	@Override
	public MetricMessage doFilterData(List<MetricData> dataList) {
		MetricMessage metricMsg = new MetricMessage(new MetricHeader(this.appId), dataList);
		return metricMsg;
	}

	
	@Override
	public List<MetricMessage> doFilterMessage(List<MetricMessage> dataList) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getAppId() {
		return appId;
	}


	public void setAppId(String appId) {
		this.appId = appId;
	}
}
