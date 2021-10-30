package com.skilldistillery.cards;

public class BlackjackHand extends Hand {
	
	public BlackjackHand(String holder) {
		super(holder);
	}

	public boolean hasBlackjack() {
		return getValue() == 21 && cards.size() == 2;
	}
	
	public boolean isSoft() {
		boolean hasAce = false;
		for (Card card : cards) {
			if (card.getRank() == Rank.ACE) {
				hasAce = true;
				break;
			}
		}
		return hasAce && getValue() == 17;
	}
	
	public boolean isBust() {
		return getValue() > 21;
	}

	@Override
	public int getValue() {
		int aces = 0;
		int value = 0;

		for (Card card : this.cards) {
			value += card.getRank().getValue();

			// count up our aces
			if (card.getRank() == Rank.ACE) {
				aces++;
			}
		}

		// harden each ace to an 11 as long as it doesn't cause the hand to bust
		for (int i = 0; (value + 10 <= 21) && (i < aces); i++) {
			value += 10;
		}

		return value;
	}

}
