package com.devcamp.j02_40;

import java.util.ArrayList;

public class CPerson {
//	khai báo các thuộc tính cùng Modifier + data type (kiểu dữ liệu)
	private int id;
	private int age;
	private String firstName;
	private String lastName;
	private ArrayList<CVehicle> vehicles;
	public String country;
	private ArrayList<CPet> pets;

//	contructor has params
//	param: id, age, firstName, lastName, country
	public CPerson(int id, int age, String firstName, String lastName, String country) {
		this.id = id;
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
	}

//	contructor has NOT params
//	param: none
	public CPerson() {
		this.id = 0;
		this.age = 25;
		this.firstName = "Bao";
		this.lastName = "Tran Gia";
		this.country = "vietnam";
		this.vehicles = new ArrayList<CVehicle>();
		this.pets = new ArrayList<CPet>();
	}

	/*
	 * thêm contructor có thêm thuộc tính cars(là một mảng), và cũng truyền thêm
	 * param cars(cùng kiểu dữ liệu arr)
	 */
	public CPerson(int id, int age, String firstName, String lastName, String country, ArrayList<CVehicle> vehicles,
			ArrayList<CPet> pets) {
		this.id = id;
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.vehicles = vehicles;
		this.pets = pets;
	}

	// method getFullName
	public String getFullName() {
		return this.lastName + " " + this.firstName;
	}
	
//	method addVehicle
	public void addVehicle(CVehicle paramVehicle) {
		this.vehicles.add(paramVehicle);
	}
	
//	method printAllVehicles
	public void printAllVehicles() {
		System.out.println("My vehicle collection:");
		for (int counter = 0; counter < this.vehicles.size(); counter++) {
			System.out.println(this.vehicles.get(counter).getBrand());
		}
	}

//	method addPet
	public void addPet(CPet paramPet) {
		this.pets.add(paramPet);
	}

//	method printAllPets
	public void printAllPets() {
		System.out.println("My pet collection:");
		for (int counter = 0; counter < this.pets.size(); counter++) {
			System.out.println(this.pets.get(counter).getName());
		}
	}

//	method removePetByIndex
	public void removePetByIndex(int paramIndex) {
		this.pets.remove(paramIndex);
	}

//	method printPerson
	public void printPerson() {
		System.out.println("My name is " + this.getFullName());
		System.out.println("I live in " + this.country);
		System.out.println("And I am " + this.age + " years old");
		printAllVehicles();
		printAllPets();
	}

//	thêm method để set get
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
