package com.devcamp.oop2;

class Animal2 {
	void eat() {
		System.out.println("eating...");
	}
}
class Dog2 extends Animal2 {

	void eat() {
		System.out.println("eating bread...");
	}
	void bark() {
		System.out.println("barking...");
	}
	void work() {
		super.eat();
		bark();
	}
}

public class TestSuper2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog2 d = new Dog2();
		d.work();
	}

}
