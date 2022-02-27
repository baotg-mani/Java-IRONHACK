package com.devcamp;

import java.util.ArrayList;

public class Worker extends Person {
	private int salary;
	
	public Worker() {
		super();
		this.salary = 10000000;
		this.setPersonType(PersonType.WORKER);
	}

	public Worker(int age, String gender, String name, Address address) {
		super(age, gender, name, address);
		this.salary = 10000000;
		this.setPersonType(PersonType.WORKER);
	}
	
	public Worker(int age, String gender, String name, Address address, ArrayList<Animals> listPet, ArrayList<IPlayer> listPlay) {
		super(age, gender, name, address, listPet, listPlay);
		this.salary = 10000000;
		this.setPersonType(PersonType.WORKER);
	}
	
	public Worker(int age, String gender, String name, Address address, ArrayList<Animals> listPet, ArrayList<IPlayer> listPlay, ArrayList<Ball> listBall) {
		super(age, gender, name, address, listPet, listPlay, listBall);
		this.salary = 10000000;
		this.setPersonType(PersonType.WORKER);
	}

	public Worker(int salary) {
		super();
		this.salary = salary;
		this.setPersonType(PersonType.WORKER);
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void working() {
		System.out.println("Worker working!");
	}

	@Override
	public void eat() {
		System.out.println("Worker eat!");
	}

	@Override
	public void gotoShop() {
		// TODO Auto-generated method stub
		System.out.println("Worker goto shop!");
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println("Worker playing!");
	}

}
