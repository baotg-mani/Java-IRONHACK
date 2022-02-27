package com.devcamp;

public class CDPlayer implements IPlayer {
	private PlayerType type = PlayerType.CDPLAYER;
	
	public CDPlayer() {
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
		System.out.println("CD play..");
		return null;
	}

	@Override
	public String getStop() {
		System.out.println("CD stop..");
		return null;
	}

	@Override
	public String getPause() {
		System.out.println("CD pause..");
		return null;
	}

	@Override
	public String getReverse() {
		System.out.println("CD reverse..");
		return null;
	}

}
