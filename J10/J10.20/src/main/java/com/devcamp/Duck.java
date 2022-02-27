package com.devcamp;

public class Duck extends Animals {
	private String beakColor;
		
	public Duck() {
		this.age = 2;
		this.gender = "female";
		this.beakColor = "white";
	}

	public Duck(int age, String gender, String beakColor) {
		this.age = age;
		this.gender = gender;
		this.beakColor = beakColor;
	}

	public String getBeakColor() {
		return beakColor;
	}

	public void setBeakColor(String beakColor) {
		this.beakColor = beakColor;
	}
	@Override
	public boolean isMammal() {
		return false;	
	}

	
	@Override
	public void mate() {
		System.out.println("Animals mate!");
	}
	
	public void swim() {
		System.out.println("Duck swim!");
	}
	
	public void quack() {
		System.out.println("Duck quack!");
	}

	@Override
	public void animalSound() {
		// TODO Auto-generated method stub
		System.out.println("Duck animal sound ...");
	}

	@Override
	public void sleep() {
		// TODO Auto-generated method stub
		System.out.println("Duck sleeping ...");
	}
}
