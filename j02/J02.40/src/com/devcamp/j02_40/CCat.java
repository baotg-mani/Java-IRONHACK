package com.devcamp.j02_40;

public class CCat extends CPet {
	public CCat() {
		
	}
	public CCat(String name) {
		super.name = name;
	}
	
	public void bark() {
		System.out.println("meooooo ...");
	}
	
	public void print() {
		System.out.println("Mèo của cô chủ đây!");
		System.out.println("Tên: " + super.name);
	}
	
	public void play() {
		System.out.println("Chơi với mèo bị đứt tay!");
	}
}

