package com.example.demo;

import java.util.ArrayList;

public class Student extends Person {
	private int studentId;
//	private float averageMark;
	private ArrayList<Subject> listSubject;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int age, String gender, String name, Address address) {
		super(age, gender, name, address);
		// TODO Auto-generated constructor stub
	}

//	public Student(int age, String gender, String name, Address address, int studentId, float averageMark) {
//		super(age, gender, name, address);
//		this.studentId = studentId;
//		this.averageMark = averageMark;
//	}

	public Student(int age, String gender, String name, Address address, int studentId,
			ArrayList<Subject> listSubject) {
		super(age, gender, name, address);
		this.studentId = studentId;
		this.listSubject = listSubject;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

//	public float getAverageMark() {
//		return averageMark;
//	}
//
//	public void setAverageMark(float averageMark) {
//		this.averageMark = averageMark;
//	}

	public ArrayList<Subject> getListSubject() {
		return listSubject;
	}

	public void setListSubject(ArrayList<Subject> listSubject) {
		this.listSubject = listSubject;
	}

	public void doHomework() {
		System.out.println("Doing homework...");
	}
}
