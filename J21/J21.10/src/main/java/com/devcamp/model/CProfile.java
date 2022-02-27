package com.devcamp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profiles")
public class CProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "birthday")
	private Date dateOfBirth;
	
	@Column(name = "address")
	private String address;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private CUser user;
	
	public CProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CProfile(long id, String phone, String gender, Date dateOfBirth, String address, CUser user) {
		super();
		this.id = id;
		this.phone = phone;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.user = user;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CUser getUser() {
		return user;
	}
	public void setUser(CUser user) {
		this.user = user;
	}
	
}
