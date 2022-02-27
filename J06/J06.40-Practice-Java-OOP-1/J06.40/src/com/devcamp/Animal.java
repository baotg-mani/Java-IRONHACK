package com.devcamp;

public class Animal {
	void eat() {
		System.out.println("eating...");
	}
}

class Dog extends Animal {
	void eat() {
		System.out.println("eating bread...");
	}
}