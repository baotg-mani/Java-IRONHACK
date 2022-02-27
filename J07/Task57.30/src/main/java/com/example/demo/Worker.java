package com.example.demo;

public class Worker extends Person {
	private int salary;

	public Worker() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Worker(int age, String gender, String name, Address address) {
		super(age, gender, name, address);
		// TODO Auto-generated constructor stub
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
}
