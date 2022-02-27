package com.devcamp.model;

public class Address {
	private String street;
	private String city;
	private String country;
	private int postcode;

	public Address() {
		super();
	}

	public Address(String street, String city, String country, int postcode) {
		super();
		this.street = street;
		this.city = city;
		this.country = country;
		this.postcode = postcode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public boolean isValidate() {
		return true;
	}
}
