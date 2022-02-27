package com.devcamp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "region")
public class CRegion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, name = "region_code")
	private String regionCode;

	@Column(name = "region_name")
	private String regionName;

	@ManyToOne
	@JoinColumn(name = "country_id")
	@JsonIgnoreProperties(value = "regions")
//    @JsonBackReference
	private CCountry country;

	@Transient
	@JsonIgnore
	private String countryName;

	public String getCountryName() {
		return getCountry().getCountryName();
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public CCountry getCountry() {
		return country;
	}

	public void setCountry(CCountry cCountry) {
		this.country = cCountry;
	}
}
