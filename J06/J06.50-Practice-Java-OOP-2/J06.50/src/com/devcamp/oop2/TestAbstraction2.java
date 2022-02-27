package com.devcamp.oop2;

abstract class Bike {
	Bike() {
		System.out.println("bike is created");
	}
	abstract void run();
	void changeGear() {
		System.out.println("gear changed");
	}
}

class Honda extends Bike {
	void run() {
		System.out.println("running safely..");
	}
}

public class TestAbstraction2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bike obj = new Honda();
		obj.run();
		obj.changeGear();
	}

}
