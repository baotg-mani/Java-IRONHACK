package com.devcamp.oop2;

public class TestStaticMethod {

	public static void main(String[] args) {
		Student.change();
		// TODO Auto-generated method stub
		Student s1 = new Student(111, "Karan");
		Student s2 = new Student(222, "Aryan");
		Student s3 = new Student(333, "Sono");

		// calling display method
		s1.display();
		s2.display();
		s3.display();
	}

}
