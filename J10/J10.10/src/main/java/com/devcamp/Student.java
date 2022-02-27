package com.devcamp;

import java.util.ArrayList;

public class Student extends Person implements ISchool {
	private int studentId;
	private ArrayList<Subject> listSubject;

	
	public Student() {
		super();
		this.studentId = 1;
		this.setPersonType(PersonType.STUDENT);
	}


	public Student(int age, String gender, String name, Address address) {
		super(age, gender, name, address);
		this.studentId = 1;
		this.setPersonType(PersonType.STUDENT);
	}


	public Student(int studentId, ArrayList<Subject> listSubject) {
		super();
		this.studentId = studentId;
		this.listSubject = listSubject;
		this.setPersonType(PersonType.STUDENT);
	}
	
	public Student(int studentId, ArrayList<Subject> listSubject, ArrayList<Animals> listPets) {
		super();
		this.studentId = studentId;
		this.listSubject = listSubject;
		this.setListPet(listPets);
		this.setPersonType(PersonType.STUDENT);
	}

	public Student(int studentId, String name, ArrayList<Subject> listSubject) {
		super();
		this.studentId = studentId;
		this.setName(name);
		this.listSubject = listSubject;
		this.setPersonType(PersonType.STUDENT);
	}
	
	public Student(int age, String gender, String name, Address address, int studentId, ArrayList<Subject> listSubject) {
		super(age, gender, name, address);
		this.studentId = studentId;
		this.listSubject = listSubject;
		this.setPersonType(PersonType.STUDENT);
	}
	
	public Student(int age, String gender, String name, Address address, int studentId, ArrayList<Subject> listSubject, ArrayList<Animals> listPets) {
		super(age, gender, name, address, listPets);
		this.studentId = studentId;
		this.listSubject = listSubject;
		this.setPersonType(PersonType.STUDENT);
		//this.setListPet(listPets);
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
		System.out.println("Student do homework!");
	}


	@Override
	public void eat() {
		System.out.println("Student eat!");		
	}
	
	@Override
	public void gotoShop() {
		// TODO Auto-generated method stub
		System.out.println("Student goto shop!");
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println("Student playing!");
	}


	@Override
	public void gotoShool() {
		// TODO Auto-generated method stub
		System.out.println("Student goto school!");
	}	
}
