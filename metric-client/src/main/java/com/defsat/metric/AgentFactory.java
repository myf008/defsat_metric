package com.defsat.metric;

import com.defsat.metric.config.ConfigConstant;
import com.defsat.metric.config.MetricConfig;
import com.defsat.metric.core.AgentBuilder;
import com.defsat.metric.core.ConfigLoader;

public class AgentFactory {
	private static IMetricAgent instance;

	public static IMetricAgent getAgent(MetricConfig config) {
		if (instance == null) {
			synchronized (IMetricAgent.class) {
				if (instance == null) {
					instance = new AgentBuilder().config(config).build();
				}
			}
		}
		return instance;
	}

	public static IMetricAgent getAgent() {
		if (instance == null) {
			MetricConfig config = ConfigLoader.loadMetricConfig(ConfigConstant.METRIC_CLIENT_PROP,
					ConfigConstant.USER_METRIC_CLIENT_PROP);
			return getAgent(config);
		} else {
			return instance;
		}
	}
	
	public static IMetricAgent getNewAgent(MetricConfig config) {
		return new AgentBuilder().config(config).build();
	}

}
