package com.devcamp.oop2;

public class OverloadingCalculation2 {

	void sum(int a, int b) {
		System.out.println("int arg method invoked");
	}

	void sum(float a, float b) {
		System.out.println("long arg method invoked");
	}

	public static void main(String args[]) {
		OverloadingCalculation2 obj = new OverloadingCalculation2();
		obj.sum(20f, 20f);// now int arg sum() method gets invoked
	}

}
