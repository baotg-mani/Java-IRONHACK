package com.devcamp.pizza365.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Entity
@Table(name = "country")
public class CCountry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "country_code", unique = true)
	private String countryCode;

	@Column(name = "country_name")
	private String countryName;

	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "country")
//    @JsonManagedReference
	private List<CRegion> regions;
	
	@Transient
	private long regionsTotal;

	public CCountry(Long id, String countryCode, String countryName, List<CRegion> regions) {
		super();
		this.id = id;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.regions = regions;
	}

	public CCountry(Long id, String countryCode, String countryName, List<CRegion> regions, long regionsTotal) {
		super();
		this.id = id;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.regions = regions;
		this.regionsTotal = regionsTotal;
	}



	public CCountry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getRegionsTotal() {
		if (this.regions != null) {
			return this.regions.size();
		} else {
			return 0;
		}
	}

	public void setRegionsTotal(long regionsTotal) {
		this.regionsTotal = regionsTotal;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<CRegion> getRegions() {
		return regions;
	}

	public void setRegions(List<CRegion> regions) {
		this.regions = regions;
	}
}
