package com.devcamp.model;

public abstract class Animals implements IAnimal {
	private int age = 1;
	private String gender = "male";

	public Animals() {
		super();
	}

	public Animals(int age, String gender) {
		super();
		this.age = age;
		this.gender = gender;
	}

	public abstract boolean isMammal();

	public abstract void mate();

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
}
