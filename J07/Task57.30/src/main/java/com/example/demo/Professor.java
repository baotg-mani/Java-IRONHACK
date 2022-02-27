package com.example.demo;

public class Professor extends Person {
	private int salary;

	public Professor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Professor(int age, String gender, String name, Address address, int salary) {
		super(age, gender, name, address);
		this.salary = salary;
		// TODO Auto-generated constructor stub
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
}
