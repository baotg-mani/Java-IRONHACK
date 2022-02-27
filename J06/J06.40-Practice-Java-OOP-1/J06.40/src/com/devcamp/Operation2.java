package com.devcamp;

public class Operation2 {
	int data = 50;
	
	void change(Operation2 op) {
		op.data = op.data + 100;// changes will be in the instance variable
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Operation2 op = new Operation2();
		System.out.println("before change " + op.data);
		op.change(op);// passing object
		System.out.println("after change " + op.data);
	}

}
