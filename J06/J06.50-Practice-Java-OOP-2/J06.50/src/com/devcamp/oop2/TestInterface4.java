package com.devcamp.oop2;
interface Printable {
	void print();
}

interface Showable extends Printable {
	void show();
}

public class TestInterface4 implements Showable {
	public static void main(String[] args) {
		TestInterface4 obj = new TestInterface4();
		obj.print();
		obj.show();
	}

	@Override
	public void print() {
		System.out.println("hello");
	}

	@Override
	public void show() {
		System.out.println("welcome");
	}

}
