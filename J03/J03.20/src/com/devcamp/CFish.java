package com.devcamp;

public class CFish extends CPet implements ISwimable {
	
	public CFish() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CFish(int age, String name) {
		super(age, name);
		this.setAnimalClass(AnimalClass.fish);
		// TODO Auto-generated constructor stub
	}
	
	public CFish(String name, int age) {
		super(age, name, AnimalClass.fish);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void animalSound() {
		// TODO Auto-generated method stub
		System.out.println("Fish sound: u u u...");
	}

	@Override
	public void swim() {
		// TODO Auto-generated method stub
		System.out.println("Fish swimming...");
	}
	protected void print () {
		System.out.println("name = " + this.name + ", age = " + this.age + ", animal = " + this.getAnimalClass());
	}
	protected void play () {
		System.out.println("CPet playing ...");
	}
	
}
