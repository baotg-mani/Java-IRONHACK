package com.devcamp.interface_as_an_parameter;

public class CIntArray implements ISumable {
	
	private int [] mIntArray;
	
	public CIntArray(int[] array) { 
		this.mIntArray = array;
	}

	@Override
	public int getSum() {
		// TODO Auto-generated method stub
		int sum = 0;
		for (int number: mIntArray) {
			sum += number;
		}
		System.out.println("Sum of mIntArray---");
		return sum;
	}
	
	public void print() {
		System.out.println("Nội dung từ class CIntArray ---");
	} 

	public int[] getmIntArray() {
		return mIntArray;
	}

	public void setmIntArray(int[] mIntArray) {
		this.mIntArray = mIntArray;
	}

}
