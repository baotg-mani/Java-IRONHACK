package com.devcamp;

public class CCar extends CVehicle {
	
	public int maxSpeedPerKm() {
		
		return 500;
	}
	public void print() {
		System.out.print("Đây là car  :  ");
		super.print();
		
	}
}