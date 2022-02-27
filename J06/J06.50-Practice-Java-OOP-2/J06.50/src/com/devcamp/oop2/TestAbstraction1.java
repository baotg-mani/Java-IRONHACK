package com.devcamp.oop2;

abstract class Shape {
	abstract void draw();
	
}
class Rectangle extends Shape {
	void draw() {
		System.out.println("drawing rectangle");
	}
}
class Circle1 extends Shape {
	void draw() {
		System.out.println("drawing circle");
	}
}

public class TestAbstraction1 {

	public static void main(String[] args) {
		Shape s = new Circle1();
		s.draw();
	}

}
