package com.devcamp;

public class CBird extends CPet implements IFlyable {
	
	public CBird() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CBird(int age, String name) {
		super(age, name);
		this.setAnimalClass(AnimalClass.birds);
		// TODO Auto-generated constructor stub
	}
	
	public CBird(String name, int age) {
		super(age, name, AnimalClass.birds);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void animalSound() {
		// TODO Auto-generated method stub
		System.out.println("Bird sound: liu lo...");
	}

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		System.out.println("Bird flying...");
	}
	protected void print () {
		System.out.println("name = " + this.name + ", age = " + this.age + ", animal = " + this.getAnimalClass());
	}
	protected void play () {
		System.out.println("CPet playing ...");
	}

}
