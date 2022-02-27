package com.devcamp.country.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "country")
public class CCountry {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long country_id;
	
	@Column(name = "country_code", unique = true)
	private String countryCode;
	
	@Column(name = "country_name")
	private String countryName;
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    @JsonManagedReference
	private Set<CRegion> regions;
	
	public CCountry() {
	}

	public CCountry(String countryCode, String countryName) {
		this.countryCode = countryCode;
		this.countryName = countryName;
	}

	public long getId() {
		return country_id;
	}

	public void setId(long country_id) {
		this.country_id = country_id;
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

	public Set<CRegion> getRegions() {
		return regions;
	}

	public void setRegions(Set<CRegion> regions) {
		this.regions = regions;
	}
}
