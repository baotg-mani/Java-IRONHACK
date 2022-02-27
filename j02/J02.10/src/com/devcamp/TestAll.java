package com.devcamp;

public class TestAll {

	public static void main(String[] args) {
		CVehicle car1 = new CCar();
		car1.print();
		car1.honk();
		System.out.println("Max speed: " + car1.maxSpeedPerKm());
		
		CMotorbike bike1 = new CMotorbike();
		bike1.print();
		bike1.honk();
		bike1.print(200);
		System.out.println("Max speed: ----------------------");
		
		CVehicle bike2 = new CMotorbike();
		bike2.print();
		bike2.honk();
		bike2.print(200);
	}
}
