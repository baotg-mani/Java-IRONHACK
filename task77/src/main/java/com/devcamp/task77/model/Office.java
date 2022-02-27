package com.devcamp.task77.model;

import javax.persistence.*;

@Entity
@Table(name = "offices")
public class Office {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "phone", nullable = false)
	private String phone;
	
	@Column(name = "address_line", nullable = false)
	private String addressLine;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "territory", nullable = false)
	private String territory;

	public Office() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Office(int id, String city, String phone, String addressLine, String state, String country,
			String territory) {
		super();
		this.id = id;
		this.city = city;
		this.phone = phone;
		this.addressLine = addressLine;
		this.state = state;
		this.country = country;
		this.territory = territory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

}
