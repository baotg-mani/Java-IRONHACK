package com.devcamp.model;

import java.util.ArrayList;

public class University {
	private int code;
	private String name;
	private ArrayList<Person> listPerson;
	
	public University() {
		super();
		// TODO Auto-generated constructor stub
	}

	public University(int code, String name, ArrayList<Person> listPerson) {
		this.code = code;
		this.name = name;
		this.listPerson = listPerson;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Person> getListPerson() {
		return listPerson;
	}

	public void setListPerson(ArrayList<Person> listPerson) {
		this.listPerson = listPerson;
	}

	
}
