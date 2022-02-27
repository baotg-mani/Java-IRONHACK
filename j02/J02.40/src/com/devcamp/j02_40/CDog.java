package com.devcamp.j02_40;

public class CDog extends CPet {
	public CDog() {
		
	}
	public CDog(String name) {
		super.name = name;
	}
	
	public void bark() {
		System.out.println("Gâuzzzzzzz");
	}
	
	public void print() {
		System.out.println("Cún của cô chủ đây!");
		System.out.println("Tên: " + super.name);
	}
	
	public void play() {
		System.out.println("Chơi với cún không bi đứt tay!");
	}

}

