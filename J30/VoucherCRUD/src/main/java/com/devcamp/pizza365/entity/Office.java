package com.devcamp.pizza365.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the offices database table.
 * 
 */
@Entity
@Table(name="offices")
@NamedQuery(name="Office.findAll", query="SELECT o FROM Office o")
public class Office  {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="address_line")
	private String addressLine;

	private String city;

	private String country;

	private String phone;

	private String state;

	private String territory;

	public Office() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddressLine() {
		return this.addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTerritory() {
		return this.territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

}