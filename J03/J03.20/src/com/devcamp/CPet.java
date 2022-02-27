package com.devcamp;

public abstract class CPet extends CAnimal {
	protected int age;
	protected String name;
	
	public CPet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CPet(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	
	public CPet(int age, String name, AnimalClass animalClass) {
		super();
		this.age = age;
		this.name = name;
		this.setAnimalClass(animalClass);
	}

	protected void bark () {
		System.out.println("CPet barking ...");
	}
	protected void print () {
		System.out.println("name = " + this.name + ", age = " + this.age + ", animal = " + this.getAnimalClass());
	}
	protected void play () {
		System.out.println("CPet playing ...");
	}

}
