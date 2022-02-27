package com.devcamp.p2;

import com.devcamp.p1.A;

public class D {
	public static void main(String[] args) {
		A a = new A();

//		a.i = 2;  //private
//		a.j = 3;  //default
//		a.k = 4; //protect
		a.l = 5; //public
	}
}
