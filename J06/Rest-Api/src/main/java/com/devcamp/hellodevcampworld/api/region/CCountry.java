package com.devcamp.hellodevcampworld.api.region;

import java.util.ArrayList;

public class CCountry {
	private String countryCode;
	private String countryName;
	private ArrayList <CRegion> regions;
	public CCountry(String cName, String rName) {
		this.countryName = cName;
		this.countryCode = rName;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
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
	 * @return the regions
	 */
	public ArrayList<CRegion> getRegions() {
		return regions;
	}
	/**
	 * @param regions the regions to set
	 */
	public void setRegions(ArrayList<CRegion> regions) {
		this.regions = regions;
	}
	@Override
	public String toString() {
		return "CCountry [countryCode=" + countryCode + ", countryName=" + countryName + ", regions=" + regions + "]";
	}

}
