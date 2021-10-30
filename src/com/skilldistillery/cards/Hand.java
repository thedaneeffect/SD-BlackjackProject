package com.skilldistillery.cards;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {

	protected final List<Card> cards;
	private final String holder;

	public Hand(String holder) {
		this.holder = holder;
		cards = new ArrayList<>();
	}

	public abstract int getValue();

	public String getHolder() {
		return holder;
	}

	public void add(Card card) {
		cards.add(card);
	}

	public void clear() {
		cards.clear();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			sb.append(card.toString());
		}
		sb.append('=').append(getValue());
		return sb.toString();
	}

}
