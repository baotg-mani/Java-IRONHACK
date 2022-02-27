package com.devcamprelationship.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class CCustomer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@OneToMany(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			mappedBy = "customer")
	private Set<COrder> posts;
	
	public CCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CCustomer(long id, String username, String firstname, String lastname, Set<COrder> posts) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.posts = posts;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Set<COrder> getPosts() {
		return posts;
	}
	public void setPosts(Set<COrder> posts) {
		this.posts = posts;
	}
	
}
