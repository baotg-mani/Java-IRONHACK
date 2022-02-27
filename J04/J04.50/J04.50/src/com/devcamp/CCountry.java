package com.devcamp;

import java.util.ArrayList;

public class CCountry {

	private String countryCode;
	private String countryName;
	private ArrayList<CRegion> regions;
	
	public CCountry(String code, String name) {
		this.countryCode = code;
		this.countryName = name;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public ArrayList<CRegion> getRegions() {
		return regions;
	}
	public void setRegions(ArrayList<CRegion> regions) {
		this.regions = regions;
	}
	
	public String toString() {
		return this.countryCode + "-" + this.countryName;
	}
	
}
