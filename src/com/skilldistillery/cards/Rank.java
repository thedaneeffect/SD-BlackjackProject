package com.skilldistillery.cards;

public enum Rank {
	ACE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	// KNIGHT (skipped)
	QUEEN(10, 0x1F0AD),
	KING(10, 0x1F0AE);
	
	private final int value;
	private final int unicode;
	
	Rank(int value) {
		this(value, -1);
	}
	
	Rank(int value, int unicode) {
		this.value = value;
		this.unicode = unicode;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getUnicode() {
		if (unicode == -1) {
			return 0x1F0A1 + ordinal();
		}
		return unicode;
	}
}
