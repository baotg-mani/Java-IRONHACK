package com.devcamp.task77.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "countries")
public class CCountry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long countryId;

	@NotEmpty(message = "country code không được để trống")
	@Size(min = 2, message = "country code phải có tối thiểu 2 ký tự")
	@Column(name = "countryCode", unique = true, nullable = false)
	private String countryCode;

	@NotEmpty(message = "country name không được để trống")
	@Column(name = "countryName", nullable = false)
	private String countryName;

	public CCountry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CCountry(Long countryId,
			@NotEmpty(message = "country code không được để trống") @Size(min = 2, message = "country code phải có tối thiểu 2 ký tự") String countryCode,
			@NotEmpty(message = "country name không được để trống") String countryName) {
		super();
		this.countryId = countryId;
		this.countryCode = countryCode;
		this.countryName = countryName;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
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

}
