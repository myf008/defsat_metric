package com.defsat.metric.filter;

import java.util.List;

import com.defsat.metric.metric.MetricData;
import com.defsat.metric.metric.MetricMessage;

public interface IMetricFilter {
	
	MetricMessage doFilterData(List<MetricData> dataList);
	
	List<MetricMessage> doFilterMessage(List<MetricMessage> dataList);
}
