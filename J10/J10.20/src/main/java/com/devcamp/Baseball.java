package com.devcamp;

public class Baseball extends Ball {

	public Baseball() {
		super();
		this.setBrandName("MLB");
		this.setBallType(BallType.BASEBALL);
	}
	
	public Baseball(String brandName) {
		super(brandName);
		this.setBallType(BallType.BASEBALL);
	}

	@Override
	void bounce() {
		System.out.println("bóng chày nảy...");
	}

	@Override
	public void toss() {
		System.out.println("ném bóng chày...");
	}
}
