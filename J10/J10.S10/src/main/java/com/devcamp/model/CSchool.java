package com.devcamp.model;

import java.util.ArrayList;

public class CSchool {
	private ArrayList<CClass> listClass;

	public CSchool() {
		super();
	}

	public CSchool(ArrayList<CClass> listClass) {
		super();
		this.listClass = listClass;
	}

	public ArrayList<CClass> getListClass() {
		return listClass;
	}

	public void setListClass(ArrayList<CClass> listClass) {
		this.listClass = listClass;
	}
}
