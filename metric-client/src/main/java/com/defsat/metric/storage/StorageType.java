package com.defsat.metric.storage;


public enum StorageType {
	INFLUXDB("influxdb"),
	KAFKA("kafka");
	
	private String value;
	
	StorageType(String value){
		this.value = value;
	}
	
	public static StorageType getStorageType(String value){
		
		for(StorageType entry:StorageType.values()){
			if(entry.getValue().equalsIgnoreCase(value)){
				return entry;
			}
		}
		
		return INFLUXDB;
	}


	public String getValue() {
		return value;
	}
	
	
}
