package com.devcamp;

public class CMotorbike extends CVehicle {
	public CMotorbike() {
		// TODO Auto-generated constructor stub
	}

	public CMotorbike(String brand, int yearManufactured) {
		super(brand, yearManufactured);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int maxSpeedPerKm() {
		// TODO Auto-generated method stub
		return 50 + super.maxSpeedPerKm();
	}
}

