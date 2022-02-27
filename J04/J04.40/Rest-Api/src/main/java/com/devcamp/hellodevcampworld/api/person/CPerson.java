package com.devcamp.hellodevcampworld.api.person;

public class CPerson {
	private static String username;
	private String firstname;
	private String lastname;
	
	public CPerson(String username, String firstname, String lastname) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public static String getUsername() {
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
	
	
}
