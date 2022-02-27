package com.devcamp;

public class Subject {
	private String subTitle;
	private int subId;
	private Professor teacher;
	public Subject() {
		super();
		this.subTitle = "Math";
		this.subId = 1;
		this.teacher = new Professor();
	}
	public Subject(String subTitle, int subId, Professor teacher) {
		super();
		this.subTitle = subTitle;
		this.subId = subId;
		this.teacher = teacher;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public Professor getTeacher() {
		return teacher;
	}
	public void setTeacher(Professor teacher) {
		this.teacher = teacher;
	}
	
}
