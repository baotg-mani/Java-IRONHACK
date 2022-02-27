package com.devcamp;

public class CDog extends CPet implements IRunable, IBarkable {
	
	public CDog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CDog(int age, String name) {
		super(age, name);
		this.setAnimalClass(AnimalClass.mammals);
		// TODO Auto-generated constructor stub
	}
	
	public CDog(String name, int age) {
		super(age, name, AnimalClass.mammals);
		// TODO Auto-generated constructor stub
	}
		
	protected void bark () {
		System.out.println("Dog barking...");
	}
	protected void print () {
		System.out.println("name = " + this.name + ", age = " + this.age + ", animal = " + this.getAnimalClass());
	}
	protected void play () {
		System.out.println("Dog playing...");
	}
	@Override
	public void animalSound() {
		// TODO Auto-generated method stub
		System.out.println("Dog sound: gau gau...");
	}
	@Override
	public void barking() {
		// TODO Auto-generated method stub
		System.out.println("Dog barking...");
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Dog running...");
	}	
}
