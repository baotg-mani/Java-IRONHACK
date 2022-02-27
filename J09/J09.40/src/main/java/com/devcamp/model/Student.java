package com.devcamp.model;

import java.util.ArrayList;

public class Student extends Person implements ISchool {
	private int studentId;
	private ArrayList<Subject> listSubject;

	public Student() {
		super();
	}

	public Student(int age, String gender, String name, Address address, int studentId, ArrayList<Subject> listSubject) {
		super(age, gender, name, address);
		this.studentId = studentId;
		this.listSubject = listSubject;
	}

	public Student(int age, String gender, String name, Address address, int studentId, ArrayList<Subject> listSubject, ArrayList<Animals> listPet) {
		super(age, gender, name, address, listPet);
		this.studentId = studentId;
		this.listSubject = listSubject;
	}

	@Override
	public void eat() {
		System.out.println("student eats vegetable");
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public ArrayList<Subject> getListSubject() {
		return listSubject;
	}

	public void setListSubject(ArrayList<Subject> listSubject) {
		this.listSubject = listSubject;
	}

	public void doHomework() {
		System.out.println("Doing homework...");
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

	@Override
	public void gotoSchool() {
		// TODO Auto-generated method stub
		
	}
}
