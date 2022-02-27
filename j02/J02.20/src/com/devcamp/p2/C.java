package com.devcamp.p2;

import com.devcamp.p1.A;

public class C extends A {

	public static void main(String[] args) {
		C c = new C();
//		c.i = 0; // private
//		c.j = 0; //default
		c.k = 4; //protected
		c.l = 5; //public
	}

}
