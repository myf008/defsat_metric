package com.defsat.metric.metric;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MetricHeader {

	private String appId;
	private String host;
	private String version;

	
	public MetricHeader() {
		
	}
	
	public MetricHeader(String appId) {
		this.appId = appId;
		this.host = getLocalAdress();
		this.version = "0.1.0";
	}
	
	public MetricHeader(String appId, String host, String version) {
		this.appId = appId;
		this.host = host;
		this.version = version;
	}

	private String getLocalAdress(){
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "";
		}
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "MetricHeader [appId=" + appId + ", host=" + host + ", version=" + version + "]";
	}
	
	
}
