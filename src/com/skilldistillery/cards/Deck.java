package com.skilldistillery.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Deck {

	private final List<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
		
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
	}

	public int remaining() {
		return cards.size();
	}

	public Card deal() {
		Card card = cards.get(0);
		cards.remove(0);
		return card;
	}

	public void deal(Hand hand) {
		hand.add(this.deal());
	}
	
	public void retrieve(Hand hand) {
		cards.addAll(hand.cards);
		hand.clear();
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

}
