package com.devcamp.oop2;

public class Counter {
	int count = 0;
	
	Counter() {
		count++;
		System.out.println(count);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Counter c1 = new Counter();
		Counter c2 = new Counter();
		Counter c3 = new Counter();
	}

}
