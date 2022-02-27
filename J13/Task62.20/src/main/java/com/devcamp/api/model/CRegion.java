package com.devcamp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "region")
public class CRegion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long region_id;

	@Column(name = "region_code", unique = true)
	private String regionCode;

	@Column(name = "region_name")
	private String regionName;

	@ManyToOne
	@JoinColumn(name = "country_id")
	/* ở đây thuộc tính này không thêm get set nên không cần Annotation @Ignore hay
 	@JsonBackReference */
	private CCountry country;

	public CRegion() {
	}

	public CRegion(String regionCode, String regionName) {
		this.regionCode = regionCode;
		this.regionName = regionName;
	}

	public long getId() {
		return region_id;
	}

	public void setId(long region_id) {
		this.region_id = region_id;
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
