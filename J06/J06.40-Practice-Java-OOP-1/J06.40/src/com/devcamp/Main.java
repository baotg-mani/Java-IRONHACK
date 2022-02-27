package com.devcamp;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OverloadingExample example = new OverloadingExample();
		System.out.println(example.add(2, 3));
		System.out.println(example.add(1, 2, 3));
		System.out.println("-----");
		
		Animal animal = new Animal();
		animal.eat();
		
		Dog dog = new Dog();
		dog.eat();
	}

}
