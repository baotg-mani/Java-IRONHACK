package com.devcamp.hellodevcampworld.json;

import java.util.List;

public class CCountry {
	private String countryName;
	private String countryShortCode;
	private List<CRegion> regions;
	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the countryShortCode
	 */
	public String getCountryShortCode() {
		return countryShortCode;
	}
	/**
	 * @param countryShortCode the countryShortCode to set
	 */
	public void setCountryShortCode(String countryShortCode) {
		this.countryShortCode = countryShortCode;
	}
	/**
	 * @return the regions
	 */
	public List<CRegion> getRegions() {
		return regions;
	}
	/**
	 * @param regions the regions to set
	 */
	public void setRegions(List<CRegion> regions) {
		this.regions = regions;
	}
}
