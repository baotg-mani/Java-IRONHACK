package com.devcamp;

public class Zebra extends Animals {
	private boolean is_wild;
		
	public Zebra() {
		this.age = 2;
		this.gender = "female";
		this.is_wild = true;
	}

	public Zebra(int age, String gender, boolean is_wild) {
		this.age = age;
		this.gender = gender;
		this.is_wild = is_wild;
	}

	public boolean isIs_wild() {
		return is_wild;
	}

	public void setIs_wild(boolean is_wild) {
		this.is_wild = is_wild;
	}

	@Override
	public boolean isMammal() {
		return true;
	}
	
	@Override
	public void mate() {
		System.out.println("Zebra mate!");
	}
	
	public void run() {
		System.out.println("Zebra run!");
	}
	
	@Override
	public void animalSound() {
		// TODO Auto-generated method stub
		System.out.println("Zebra animal sound ...");
	}

	@Override
	public void sleep() {
		// TODO Auto-generated method stub
		System.out.println("Zebra sleeping ...");
	}
}
