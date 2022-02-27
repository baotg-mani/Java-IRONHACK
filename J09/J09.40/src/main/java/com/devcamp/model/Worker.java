package com.devcamp.model;

import java.util.ArrayList;

public class Worker extends Person {
	private int salary;

	public Worker() {
		super();
	}

	public Worker(int age, String gender, String name, Address address, int salary, ArrayList<Animals> listPet) {
		super(age, gender, name, address, listPet);
		this.salary = salary;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public void eat() {
		System.out.println("worker eats pork");
	}

	@Override
	public void animalSound() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sleep() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gotoShop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
}
