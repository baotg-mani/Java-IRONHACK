package com.example.demo;

public class Person {
	private int age;
	private String gender;
	private String name;
	private Address address;
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(int age, String gender, String name, Address address) {
		super();
		this.age = age;
		this.gender = gender;
		this.name = name;
		this.address = address;
	}
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
	
}
