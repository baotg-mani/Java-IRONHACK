package com.devcamp;

import java.util.ArrayList;

public class CPerson {
	 private int id;
	 private int age;
	 private String firstName;
	 private String lastName;
	 public String country;
	 private ArrayList<String> cars;
	 private ArrayList<CPet> pets;
	 
	 public CPerson() {
		this.id = 0;
		this.age = 0;
		this.firstName = "";
		this.lastName = "";
		this.country = "Vietnam"; 
		this.cars = new ArrayList<String>();
		this.pets = new ArrayList<CPet>();
	 }

	public CPerson(int id, int age, String firstName, String lastName, String country) {
		//super();
		this.id = id;
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.cars = new ArrayList<String>();
		this.pets = new ArrayList<CPet>();
	}
	 
	public CPerson(int id, int age, String firstName, String lastName) {
		//super();
		this.id = id;
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = "Vietnam"; 
		this.cars = new ArrayList<String>();
		this.pets = new ArrayList<CPet>();
	}
	public CPerson(int id) {
		super();
		this.id = id;
		this.age = 0;
		this.firstName = "";
		this.lastName = "";
		this.country = ""; 
		this.cars = new ArrayList<String>();
		this.pets = new ArrayList<CPet>();
	}
	
	
	public CPerson(int id, int age, String firstName, String lastName, String country, ArrayList<String> cars) {
		super();
		this.id = id;
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.cars = cars;
		this.pets = new ArrayList<CPet>();
	}
	
	public CPerson(int id, int age, String firstName, String lastName, String country, ArrayList<String> cars, ArrayList<CPet> pets) {
		super();
		this.id = id;
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.cars = cars;
		this.pets = pets;
	}

	public String getFullname() {
		String fullName = this.firstName + " " + this.lastName;
		return fullName;
		//return this.fullName();
	}
	
	/*
	private String fullName() {
		return this.firstName + " " + this.lastName;
	}
	*/

	public void printPerson() {
		System.out.println("Id = " + this.id + "; fullname = " + this.getFullname() + "; age = " + this.age + "; country = " + this.country);
		printCars();
		printPets();
	}
	
	private void printCars( ) {
		System.out.println("Danh sách cars:");
		for (String car : this.cars) {
			System.out.println(car);
		}
	}
	
	private void printPets( ) {
		System.out.println("Danh sách pets:");
		for (CPet pet: this.pets) {
			pet.print();
			pet.bark();
			pet.eat();
			pet.animalSound();
//			if (pet.getAnimalClass() == AnimalClass.mammals) {
//				((CCat)pet).run();
//				((CDog)pet).run();
//			} else if (pet.getAnimalClass() == AnimalClass.birds) {
//				((CBird)pet).fly();
//			} else {
//				((CFish)pet).swim();
//			} 
		}
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public ArrayList<String> getCars() {
		return cars;
	}

	
	public ArrayList<CPet> getPets() {
		return pets;
	}

	public void setPets(ArrayList<CPet> pets) {
		this.pets = pets;
	}

	public void setCars(ArrayList<String> cars) {
		this.cars = cars;
	}
	
	public void addCar(String car) {
		this.cars.add(car);
	}
	
	public void addCars(ArrayList<String> cars) {
		this.cars.addAll(cars);
	}
	
	public void addPet(CPet pet) {
		this.pets.add(pet);
	}
	
	public void addPets(ArrayList<CPet> pets) {
		this.pets.addAll(pets);
	}
	
	public void removePet(int ind) {
		this.pets.remove(ind);
	}
	
	public void removePet(CPet pet) {
		this.pets.remove(pet);
	}
}