package com.devcamp.model;

public class CRegion {
	private String regionCode;
	private String regionName;
	public CRegion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CRegion(String regionCode, String regionName) {
		super();
		this.regionCode = regionCode;
		this.regionName = regionName;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
}
