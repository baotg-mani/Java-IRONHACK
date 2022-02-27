package com.devcamp;

import java.util.ArrayList;

public class Professor extends Person implements ISchool {
	private int salary;
	
	public Professor() {
		super();
		this.salary = 25000000;
		this.setPersonType(PersonType.PROFESSOR);
	}

	public Professor(int age, String gender, String name, Address address) {
		super(age, gender, name, address);
		this.salary = 25000000;
		this.setPersonType(PersonType.PROFESSOR);
	}

	public Professor(int age, String gender, String name, Address address, ArrayList<Animals> listPet, ArrayList<IPlayer> listPlay) {
		super(age, gender, name, address, listPet, listPlay);
		this.salary = 25000000;
		this.setPersonType(PersonType.PROFESSOR);
	}
	
	public Professor(int age, String gender, String name, Address address, ArrayList<Animals> listPet, ArrayList<IPlayer> listPlay, ArrayList<Ball> listBall) {
		super(age, gender, name, address, listPet, listPlay, listBall);
		this.salary = 25000000;
		this.setPersonType(PersonType.PROFESSOR);
	}
	
	public Professor(int salary) {
		super();
		this.salary = salary;
		this.setPersonType(PersonType.PROFESSOR);
	}

	public Professor(int age, String gender, String name, Address address, int salary) {
		super(age, gender, name, address);
		this.salary = salary;
	}
	
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void teaching() {
		System.out.println("Professor teaching!");
	}

	@Override
	public void eat() {
		System.out.println("Professor eat!");
	}

	@Override
	public void gotoShop() {
		// TODO Auto-generated method stub
		System.out.println("Professor goto shop!");
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println("Professor playing!");
	}

	@Override
	public void gotoShool() {
		// TODO Auto-generated method stub
		System.out.println("Professor goto school!");
	}	
}
