package com.devcamp.j02_40;

public class CMotorbike extends CVehicle {
	public CMotorbike() {
	}
	
	@Override
	public void print() {
		System.out.println("Thông tin Motorbike mới hơn nè!");
	}
	
	@Override
	public void honk() {
		System.out.println("Bíp Bíp");
	}
}
