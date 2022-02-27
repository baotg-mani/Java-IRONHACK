package com.devcamp;

public class CCat extends CPet implements IRunable, IBarkable {
	
	public CCat() {
		super();
		// TODO Auto-generated constructor stub		
	}
	public CCat(int age, String name) {
		super(age, name, AnimalClass.mammals);
		// TODO Auto-generated constructor stub
	}
	
	public CCat(String name, int age) {
		super(age, name);
		this.setAnimalClass(AnimalClass.mammals);
		// TODO Auto-generated constructor stub
	}
	
	protected void bark () {
		System.out.println("Cat barking...");
	}
	protected void print () {
		System.out.println("name = " + this.name + ", age = " + this.age + ", animal = " + this.getAnimalClass());
	}
	protected void play () {
		System.out.println("Cat playing...");
	}
	@Override
	public void animalSound() {
		// TODO Auto-generated method stub
		System.out.println("Cat sound: meo meo...");
	}
	@Override
	public void barking() {
		// TODO Auto-generated method stub
		System.out.println("Cat barking...");
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Cat running...");
	}
}

