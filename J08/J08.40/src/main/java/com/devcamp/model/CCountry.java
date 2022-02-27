package com.devcamp.model;

import java.util.ArrayList;

public class CCountry {
	private String countryCode;
	private String countryName;
	private ArrayList<CRegion> regions;
	public CCountry() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CCountry(String countryCode, String countryName, ArrayList<CRegion> regions) {
		super();
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.regions = regions;
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
}
