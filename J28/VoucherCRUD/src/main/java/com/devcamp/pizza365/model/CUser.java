package com.devcamp.pizza365.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "p_user")
public class CUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	private String firstname;

	@Column(name = "last_name")
	private String lastname;

	@Column(name = "user_name", unique = true)
	private String username;

	@Column(name = "country")
	private String country;

	@Column(name = "subject")
	private String subject;

	@Column(name = "customer_type")
	private String customerType;

	@Column(name = "register_status")
	private String registerStatus;

	@OneToMany(targetEntity = COrder.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<COrder> orders;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(String registerStatus) {
		this.registerStatus = registerStatus;
	}

	public List<COrder> getOrders() {
		return orders;
	}

	public void setOrders(List<COrder> orders) {
		this.orders = orders;
	}

}
