package com.devcamp;

public class CVehicle {
	private String brand = "Honda";
	private int yearManufactured = 2020;

//	constructor
	public CVehicle(String brand) {
		this.brand = brand;
		this.yearManufactured = 2020;
	}
	/*@param yearManufactured*/
//	constructor
	public CVehicle(int yearManufactured) {
		this.brand = "Vin";
		this.yearManufactured = yearManufactured;
	}

//	constructor
	public CVehicle() {
		super();
		// TODO Auto-generated constructor stub
		this.brand = "Vin";
		yearManufactured = 2020;
	}

	/* @param brand
	 * @param yearManufactured*/
//	constructor
	public CVehicle(String brand, int yearManufactured) {
		this.brand = brand;
		this.yearManufactured = yearManufactured;
	}
// 	method maxSpeedPerKm
	public int maxSpeedPerKm() {
		return 30;
	}
//	method print
	public void print() {
		String vehic = "Thông tin vehicle: ";
		System.out.println(vehic + this.brand + " " + this.yearManufactured+ " " + this.maxSpeedPerKm());
	}
//	method print có param
	public void print(int speed) {
		String vehic = "Thông tin vehicle: ";
		System.out.println(vehic + this.brand + " " + this.yearManufactured + " , tốc độ: " + speed);
	}
//	method honk (còi xe từng loại)
	public void honk() {
		System.out.println("To ti te!");
	}
}
