package com.devcamp.j02_40;

public class CCar extends CVehicle {
	protected String brand;
	protected String model;
	
	public CCar() {}
	public CCar(String paramBrand, String paramModel) {
		this.brand = paramBrand;
		this.model = paramModel;
	}
	
	@Override
	public void print() {
		System.out.println("Thông tin Car mới nhất đây!");
	}
	
	@Override
	public void honk() {
		System.out.println("Paz Paz!");
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
}
