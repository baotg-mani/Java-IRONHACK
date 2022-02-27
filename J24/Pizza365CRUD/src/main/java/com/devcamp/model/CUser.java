package com.devcamp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user")
public class CUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "user")
//	@JsonManagedReference
	private Set<COrder> orders;

	public CUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CUser(long id, String fullname, String email, String phone, String address, Set<COrder> orders) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.orders = orders;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<COrder> getOrders() {
		return orders;
	}

	public void setOrders(Set<COrder> orders) {
		this.orders = orders;
	}

}
