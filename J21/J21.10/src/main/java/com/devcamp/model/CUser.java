package com.devcamp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class CUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "username")
	private String username;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lasyname")
	private String lastname;

	@OneToMany(mappedBy = "createdBy",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private Set<CPost> posts;

	@OneToOne(mappedBy = "user",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private CProfile profile;

	public CUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CUser(long id, String username, String firstname, String lastname, Set<CPost> posts, CProfile profile) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.posts = posts;
		this.profile = profile;
	}

	public CProfile getProfile() {
		return profile;
	}

	public void setProfile(CProfile profile) {
		this.profile = profile;
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

	public Set<CPost> getPosts() {
		return posts;
	}

	public void setPosts(Set<CPost> posts) {
		this.posts = posts;
	}

}
