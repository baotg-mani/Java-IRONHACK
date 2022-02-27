package com.devcamp.hellodevcampworld.api.region;

public class CRegion {
	private String regionCode;
	private String regionName;
	public CRegion(String string, String string2) {
		this.regionName = string;
		this.setRegionCode(string2);
	}
	/**
	 * @return the regionCode
	 */
	public String getRegionCode() {
		return regionCode;
	}
	/**
	 * @param regionCode the regionCode to set
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	/**
	 * @return the regionName
	 */
	public String getRegionName() {
		return regionName;
	}
	/**
	 * @param regionName the regionName to set
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	@Override
	public String toString() {
		return "CRegion [regionCode=" + regionCode + ", regionName=" + regionName + "]";
	}

}
