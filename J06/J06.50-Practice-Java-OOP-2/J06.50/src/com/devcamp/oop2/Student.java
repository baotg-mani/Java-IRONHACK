package com.devcamp.oop2;

public class Student {
	int rollno;
	String name;
	static String college = "ITS";
	
	//static method to change the value of static variable
	static void change() {
		college = "BBDIT";
	}
	
	Student(int r, String n) {
		rollno = r;
		name = n;
	}
	
	void display() {
		System.out.println(rollno + " " + college);
	}
}
