package com.devcamp.task77.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "last name can't be empty")
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@NotEmpty(message = "first name can't be empty")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotEmpty(message = "phone number can't be empty")
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "country")
	private String country;

	@Column(name = "sales_rep_employee_number")
	private int salesRepEmployeeNumber;

	@Column(name = "credit_limit")
	private int creditLimit;

	@OneToMany(mappedBy = "customerId", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private List<Payment> payments;
	
	@OneToMany(mappedBy = "customerId", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	private List<Order> orders;

	public Customer() {
		super();
	}

	public Customer(int id, @NotEmpty(message = "last name can't be empty") String lastName,
			@NotEmpty(message = "first name can't be empty") String firstName,
			@NotEmpty(message = "phone number can't be empty") String phoneNumber, String address, String city,
			String state, String postalCode, String country, int salesRepEmployeeNumber, int creditLimit) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
		this.creditLimit = creditLimit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getSalesRepEmployeeNumber() {
		return salesRepEmployeeNumber;
	}

	public void setSalesRepEmployeeNumber(int salesRepEmployeeNumber) {
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
	}

	public int getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
