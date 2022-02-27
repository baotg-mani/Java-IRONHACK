package com.devcamp.model;

import java.util.ArrayList;

public abstract class Person {
	private int age;
	private String gender;
	private String name;
	private Address address;
	private ArrayList<Animals> listPet;

	public Person() {
		super();
	}

	public Person(int age, String gender, String name, Address address) {
		super();
		this.age = age;
		this.gender = gender;
		this.name = name;
		this.address = address;
	}
	
	public Person(int age, String gender, String name, Address address, ArrayList<Animals> listPet) {
		super();
		this.age = age;
		this.gender = gender;
		this.name = name;
		this.address = address;
		this.setListPet(listPet);
	}

	public abstract void eat();

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<Animals> getListPet() {
		return listPet;
	}

	public void setListPet(ArrayList<Animals> listPet) {
		this.listPet = listPet;
	}

}
