package com.devcamp;

public abstract class Animals implements IAnimal {

	protected int age;
	protected String gender;

	public Animals() {
		this.age = 1;
		this.gender = "male";
	}

	public Animals(int age, String gender) {
		this.age = age;
		this.gender = gender;
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

	public abstract boolean isMammal();

	public abstract void mate();	
}