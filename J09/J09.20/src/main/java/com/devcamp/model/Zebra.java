package com.devcamp.model;

public class Zebra extends Animals {
	private boolean isWild;

	public Zebra(boolean isWild) {
		super();
		this.isWild = isWild;
	}

	public Zebra(int age, String gender, boolean isWild) {
		super(age, gender);
		this.isWild = isWild;
	}

	@Override
	public boolean isMammal() {
		return true;
	}

	@Override
	public void mate() {
		
	}
	
	public void run() {
		System.out.println("ZEBRA runing...");
	}
	
	public boolean isWild() {
		return isWild;
	}

	public void setWild(boolean isWild) {
		this.isWild = isWild;
	}

}
