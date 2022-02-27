package com.devcamp.model;

public class Fish extends Animals {
	private boolean canEat;

	public Fish(boolean canEat) {
		super();
		this.canEat = canEat;
	}

	public Fish(int age, String gender, boolean canEat) {
		super(age, gender);
		this.canEat = canEat;
	}

	@Override
	public boolean isMammal() {
		return false;
	}

	@Override
	public void mate() {
		System.out.println("not fun");
	}

	public void swim() {
		System.out.println("FISH swimming");
	}

	public boolean isCanEat() {
		return canEat;
	}

	public void setCanEat(boolean canEat) {
		this.canEat = canEat;
	}

	@Override
	public void animalSound() {
		System.out.println("u u u");
	}

	@Override
	public void sleep() {
		System.out.println("fish leepingggg");
	}
}
