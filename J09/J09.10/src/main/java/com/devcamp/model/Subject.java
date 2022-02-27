package com.devcamp.model;

public class Subject {
	private String subTile;
	private int subId;
	private Professor teacher;

	public Subject() {
		super();
	}

	public Subject(String subTile, int subId, Professor teacher) {
		this.subTile = subTile;
		this.subId = subId;
		this.teacher = teacher;
	}

	public String getSubTile() {
		return subTile;
	}

	public void setSubTile(String subTile) {
		this.subTile = subTile;
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
