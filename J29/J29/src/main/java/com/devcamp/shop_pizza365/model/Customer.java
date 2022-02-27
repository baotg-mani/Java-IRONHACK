package com.devcamp.shop_pizza365.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String address;

	private String city;

	private String country;

	@Column(name = "credit_limit")
	private int creditLimit;

	@NotEmpty(message = "Thiếu firstname!")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotEmpty(message = "Thiếu lastname!")
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@NotEmpty(message = "Thiếu phone number!")
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "sales_rep_employee_number")
	private int salesRepEmployeeNumber;

	private String state;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonIgnoreProperties(value = "customer")
	private List<Order> orders;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonIgnoreProperties(value = "customer")
	private List<Payment> payments;

	public Customer() {
	}

	public Customer(int id, String address, String city, String country, int creditLimit,
			@NotEmpty(message = "Thiếu firstname!") String firstName,
			@NotEmpty(message = "Thiếu lastname!") String lastName,
			@NotEmpty(message = "Thiếu phone number!") String phoneNumber, String postalCode,
			int salesRepEmployeeNumber, String state, List<Order> orders, List<Payment> payments) {
		super();
		this.id = id;
		this.address = address;
		this.city = city;
		this.country = country;
		this.creditLimit = creditLimit;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.postalCode = postalCode;
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
		this.state = state;
		this.orders = orders;
		this.payments = payments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public int getSalesRepEmployeeNumber() {
		return salesRepEmployeeNumber;
	}

	public void setSalesRepEmployeeNumber(int salesRepEmployeeNumber) {
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

}
