package com.devcamp.model;

public class CStudent implements ISchool {
	//mã học sinh, tên học sinh, giới tính, ngày sinh, địa chỉ, số điện thoại liên hệ
	private String studentCode;
	private String studentName;
	private String gender;
	private String birthday;
	private String address;
	private String phoneNumber;
	public CStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CStudent(String studentCode, String studentName, String gender, String birthday, String address,
			String phoneNumber) {
		super();
		this.studentCode = studentCode;
		this.studentName = studentName;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public void goToSchool() {
		System.out.printf("học sinh %s đang đến trường...\n", getStudentName());
	}
	@Override
	public void study() {
		System.out.printf("%s đang học...\n", getStudentName());
	}
	
}
