package com.devcamp.j02_40;

public class CPet {
	private int age;
	protected String name;
	
	public CPet() {
		
	}
	
	public CPet(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void bark() {
		System.out.println("z z z ...");
	}
	
	public void print() {
		System.out.println("Thú cưng của cô chủ đây!");
		System.out.println("Tên: " + name);
	}
	
	public void play() {
		System.out.println("Cùng chơi nào!");
	}
}

