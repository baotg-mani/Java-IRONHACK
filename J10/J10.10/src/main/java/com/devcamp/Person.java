package com.devcamp;

import java.util.ArrayList;

public abstract class Person implements IAnimal, ILive {
	private int age;
	private String gender;
	private String name;
	private Address address;
	private ArrayList<Animals> listPet;
	private PersonType personType;
	private ArrayList<IPlayer> listPlay;
	
	public Person() {
		super();
		this.age = 20;
		this.gender = "male";
		this.name = "Nguyễn Văn Nam";
		this.address = new Address();
		this.listPet = new ArrayList<Animals>() {{
			add(new Duck());
			add(new Fish(4, "male", 25, true));
			add(new Zebra());
		}} ;
	}

	public Person(int age, String gender, String name, Address address) {
		super();
		this.age = age;
		this.gender = gender;
		this.name = name;
		this.address = address;
		this.listPet = new ArrayList<Animals>() {{
			add(new Duck());
			add(new Fish(4, "male", 25, true));
			add(new Zebra());
		}} ;
	}

	public Person(int age, String gender, String name, Address address, ArrayList<Animals> listPet) {
		super();
		this.age = age;
		this.gender = gender;
		this.name = name;
		this.address = address;
		this.listPet = listPet;
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

	public ArrayList<Animals> getListPet() {
		return listPet;
	}

	public void setListPet(ArrayList<Animals> listPet) {
		this.listPet = listPet;
	}

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	public abstract void eat();
	
	@Override
	public void animalSound() {
		// TODO Auto-generated method stub
		System.out.println("Person sound ...");
	}

	@Override
	public void sleep() {
		// TODO Auto-generated method stub
		System.out.println("Person sleeping ...");
	}
}
