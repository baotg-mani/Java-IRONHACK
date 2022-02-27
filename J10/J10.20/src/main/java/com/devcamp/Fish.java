package com.devcamp;

public class Fish extends Animals {
	private int size;
	private boolean canEat;
	
	public Fish() {
		this.age = 1;
		this.gender = "female";
		this.size = 5;
		this.canEat = true;
	}

	public Fish(int age, String gender, int size, boolean canEat) {
		this.age = age;
		this.gender = gender;
		this.size = size;
		this.canEat = canEat;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isCanEat() {
		return canEat;
	}

	public void setCanEat(boolean canEat) {
		this.canEat = canEat;
	}

	@Override
	public boolean isMammal() {
		return false;
	}
	
	@Override
	public void mate() {
		System.out.println("Fish mate!");
	}
	
	public void swim() {
		System.out.println("Fish swim!");
	}
	
	@Override
	public void animalSound() {
		// TODO Auto-generated method stub
		System.out.println("Fish animal sound ...");
	}

	@Override
	public void sleep() {
		// TODO Auto-generated method stub
		System.out.println("Fish sleeping ...");
	}
}
