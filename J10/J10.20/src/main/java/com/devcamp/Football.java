package com.devcamp;

public class Football extends Ball {

	public Football() {
		super();
		this.setBrandName("adidas");
		this.setBallType(BallType.FOOTBALL);
	}

	public Football(String brandName) {
		super(brandName);
		this.setBallType(BallType.FOOTBALL);
	}

	@Override
	void bounce() {
		System.out.println("bóng đá nảy...");
	}

	@Override
	public void toss() {
		System.out.println("sút bóng đá...");
	}

}
