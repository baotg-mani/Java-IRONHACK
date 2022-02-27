package com.devcamp;

public class DVDPlayer implements IPlayer {
	private PlayerType type = PlayerType.DVDPLAYER;

	public DVDPlayer() {
		super();
	}

	public PlayerType getType() {
		return type;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}

	@Override
	public String getPlay() {
		System.out.println("DVD play..");
		return null;
	}

	@Override
	public String getStop() {
		System.out.println("DVD stop..");
		return null;
	}

	@Override
	public String getPause() {
		System.out.println("DVD pause..");
		return null;
	}

	@Override
	public String getReverse() {
		System.out.println("DVD reverse..");
		return null;
	}

}
