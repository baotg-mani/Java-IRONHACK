package com.devcamp;

public class TapePlayer implements IRecorder {
	private PlayerType type = PlayerType.TAPEPLAYER;

	public TapePlayer() {
		super();
	}

	@Override
	public String getPlay() {
		System.out.println("Tape play..");
		return null;
	}

	@Override
	public String getStop() {
		System.out.println("Tape stop..");
		return null;
	}

	@Override
	public String getPause() {
		System.out.println("Tape pause..");
		return null;
	}

	@Override
	public String getReverse() {
		System.out.println("Tape reverse..");
		return null;
	}

	@Override
	public String getRecord() {
		System.out.println("Tape record..");
		return null;
	}

	public PlayerType getType() {
		return type;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}

}
