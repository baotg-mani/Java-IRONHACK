package com.devcamp.interface_as_an_parameter;

import java.util.ArrayList;

public class CIntegerArrayList implements ISumable {
	private ArrayList<Integer>  mIntegerArrayList;
	
	public CIntegerArrayList(ArrayList<Integer> arrayList) {
		this.mIntegerArrayList = arrayList;
	}

	@Override
	public int getSum() {
		// TODO Auto-generated method stub
		int sum = 0;
		for(int number: mIntegerArrayList) {
			sum += number;
		}
		System.out.println("Sum of mIntegerArrayList---");
		return sum;
	}
	
	public void print() {
		System.out.println("Nội dung từ class CIntegerArrayList ---");
	}

	public ArrayList<Integer> getmIntegerArrayList() {
		return mIntegerArrayList;
	}

	public void setmIntegerArrayList(ArrayList<Integer> mIntegerArrayList) {
		this.mIntegerArrayList = mIntegerArrayList;
	}

}
