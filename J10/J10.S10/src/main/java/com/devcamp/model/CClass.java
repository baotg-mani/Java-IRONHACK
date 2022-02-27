package com.devcamp.model;

import java.util.ArrayList;

public class CClass implements ISchool {
	//mã lớp, tên lớp, tên GVCN, số điện thoại GVCN
	private String classCode;
	private String classname;
	private String teacher;
	private String phoneNumberGV;
	private ArrayList<CStudent> listStudent;
	public CClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CClass(String classCode, String classname, String teacher, String phoneNumberGV) {
		super();
		this.classCode = classCode;
		this.classname = classname;
		this.teacher = teacher;
		this.phoneNumberGV = phoneNumberGV;
	}
	
	public CClass(String classCode, String classname, String teacher, String phoneNumberGV,
			ArrayList<CStudent> listStudent) {
		super();
		this.classCode = classCode;
		this.classname = classname;
		this.teacher = teacher;
		this.phoneNumberGV = phoneNumberGV;
		this.listStudent = listStudent;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getPhoneNumberGV() {
		return phoneNumberGV;
	}
	public void setPhoneNumberGV(String phoneNumberGV) {
		this.phoneNumberGV = phoneNumberGV;
	}
	public ArrayList<CStudent> getListStudent() {
		return listStudent;
	}
	public void setListStudent(ArrayList<CStudent> listStudent) {
		this.listStudent = listStudent;
	}
	@Override
	public void goToSchool() {
		System.out.printf("%s đang trên đường đến trường...\n", getClassname());
	}
	@Override
	public void study() {
		System.out.printf("%s đang học...\n", getClassname());
	}	
}
