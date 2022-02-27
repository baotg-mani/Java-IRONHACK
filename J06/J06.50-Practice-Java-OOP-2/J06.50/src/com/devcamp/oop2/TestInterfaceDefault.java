package com.devcamp.oop2;
interface Drawable {
	void draw();
	default void msg() {
		System.out.println("default method");
	}
}
class Rectangle1 implements Drawable {
	public void draw() {
		System.out.println("drawingg rectangle");
	}
}

public class TestInterfaceDefault {
	public static void main(String[] args) {
		Drawable d = new Rectangle1();
		d.draw();
		d.msg();
	}
}
