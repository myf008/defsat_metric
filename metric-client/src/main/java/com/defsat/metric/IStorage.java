package com.defsat.metric;

import com.defsat.metric.metric.MetricMessage;

public interface IStorage {

	void store(MetricMessage metricMsg);
}
