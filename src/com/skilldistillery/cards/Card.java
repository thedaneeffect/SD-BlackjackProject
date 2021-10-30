package com.skilldistillery.cards;

import java.util.Objects;

public class Card {

	private final Suit suit;
	private final Rank rank;

	public Card(Suit suit, Rank rank) {
		super();
		this.suit = suit;
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rank, suit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return rank == other.rank && suit == other.suit;
	}

	@Override
	public String toString() {
//		return rank + " of " + suit;

		// base unicode
		int unicode = this.rank.getUnicode();
		// adjust for suit
		unicode += 0x10 * this.suit.ordinal();
		// return escaped unicode string
		return new String(Character.toChars(unicode));
	}

}
