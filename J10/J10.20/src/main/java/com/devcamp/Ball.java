package com.devcamp;

public abstract class Ball implements ITossable {
	private String brandName;
	private BallType ballType;

	abstract void bounce();

	public Ball() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ball(String brandName) {
		super();
		this.brandName = brandName;
	}

	public abstract void toss();

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public BallType getBallType() {
		return ballType;
	}

	public void setBallType(BallType type) {
		this.ballType = type;
	}
}
