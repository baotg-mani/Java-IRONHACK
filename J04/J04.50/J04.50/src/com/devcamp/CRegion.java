package com.devcamp;

public class CRegion {
	private String regionCode;
	private String regionName;
	
	public CRegion(String regionCode, String regionName) {
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
