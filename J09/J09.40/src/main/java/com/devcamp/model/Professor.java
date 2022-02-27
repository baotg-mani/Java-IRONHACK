package com.devcamp.model;

import java.util.ArrayList;

public class Professor extends Person implements ISchool{
	private int salary;

	public Professor() {
		super();
	}

	public Professor(int age, String gender, String name, Address address, int salary) {
		super(age, gender, name, address);
		this.salary = salary;
	}

	public Professor(int age, String gender, String name, Address address, int salary, ArrayList<Animals> listPet) {
		super(age, gender, name, address, listPet);
		this.salary = salary;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void teaching() {
		System.out.println("Teaching...");
	}

	@Override
	public void eat() {
		System.out.println("professor eats beef");
	}

	@Override
	public void animalSound() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sleep() {
		System.out.println("giáo sư đang ngáy ò ò ò");
	}

	@Override
	public void gotoShop() {
		System.out.println("giáo sư shopping vơi bx và cuối tuần");
	}

	@Override
	public void play() {
		System.out.println("giáo sư chơi tenis");
	}

	@Override
	public void gotoSchool() {
		System.out.println("giáo sư 0 đến trường, dạy online");
	}
}
