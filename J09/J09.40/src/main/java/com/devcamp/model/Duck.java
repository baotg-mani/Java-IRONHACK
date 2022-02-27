package com.devcamp.model;

public class Duck extends Animals {
	private String beakColor;

	public Duck(String beakColor) {
		super();
		this.beakColor = beakColor;
	}

	public Duck(int age, String gender, String beakColor) {
		super(age, gender);
		this.beakColor = beakColor;
	}

	@Override
	public boolean isMammal() {
		return true;
	}

	@Override
	public void mate() {
		System.out.println("mating...");
	}

	public void swim() {
		System.out.println("DUCK swimming...");
	}

	public void quack() {
		System.out.println("DUCK quacking...");
	}

	public String getBeakColor() {
		return beakColor;
	}

	public void setBeakColor(String beakColor) {
		this.beakColor = beakColor;
	}

	@Override
	public void animalSound() {
		System.out.println("quạc quạc quạc");
	}

	@Override
	public void sleep() {
		System.out.println("duck sleepingggg");
	}
}
