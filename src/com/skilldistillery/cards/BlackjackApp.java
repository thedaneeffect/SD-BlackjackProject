package com.skilldistillery.cards;

import java.util.Scanner;

public final class BlackjackApp {

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			BlackjackApp app = new BlackjackApp(input);
			app.begin();
		}
	}

	private final Scanner input;
	private final Deck deck = new Deck();
	private final BlackjackHand dealer = new BlackjackHand("Dealer");
	private final BlackjackHand player = new BlackjackHand("Player");
	private BlackjackHand winner;
	private String winnerCondition;
	private int round;

	private BlackjackApp(Scanner input) {
		this.input = input;
	}

	private void setupGame() {
		deck.retrieve(dealer);
		deck.retrieve(player);

		System.out.println("The deck gets shuffled.");
		deck.shuffle();

		System.out.println("The dealer deals 2 cards for each participant.");
		for (int i = 0; i < 2; i++) {
			deck.deal(dealer);
			deck.deal(player);
		}

		BlackjackHand hand = findBlackjackHand();

		if (hand != null) {
			setWinningHandAndCondition(hand, "Blackjack!");
		}
	}

	private void printHand(BlackjackHand hand) {
		System.out.printf("%6s: %s%n", hand.getHolder(), hand);
	}

	private void printHands() {
		printHand(dealer);
		printHand(player);
	}

	private BlackjackHand findBlackjackHand() {
		if (player.hasBlackjack()) {
			if (dealer.hasBlackjack()) {
				System.out.println("Standoff!");
				round = 0; // reset game
			} else {
				return player;
			}
		}

		if (dealer.hasBlackjack()) {
			return dealer;
		}

		return null;
	}

	private void setWinningHandAndCondition(BlackjackHand hand, String condition) {
		winner = hand;
		winnerCondition = condition;
		round = 0;
	}

	private boolean playerTurn() {
		System.out.print("Would you like to (h)it or (S)tay?: ");

		char option = (input.nextLine().toLowerCase() + 's').charAt(0);

		switch (option) {
		case 'h': // hit
			deck.deal(player);

			if (player.isBust()) {
				setWinningHandAndCondition(dealer, "Player bust!");
				return true;
			} else if (dealer.getValue() >= 17 && player.getValue() > 17) {
				setWinningHandAndCondition(player, "Superior hand!");
				return true;
			}
			// TODO: tie condition or "push"

			// print hands under no special conditions
			printHands();

			return false;
		case 's': // stay
			return true; // true to end turn
		}

		return false;
	}

	private void dealerTurn() {
		if (dealer.getValue() < 17 || dealer.isSoft()) {
			System.out.println("The dealer hits.");
			deck.deal(dealer);
		} else {
			System.out.println("The dealer stays.");
		}

		if (dealer.isBust()) {
			setWinningHandAndCondition(player, "Dealer bust!");
		} else if (dealer.getValue() == 21) {
			setWinningHandAndCondition(dealer, "Hand of 21!");
		}
	}

	public void begin() {
		while (true) {
			if (round == 0) {
				setupGame();
			}

			printHands();

			// check for winner before turns
			if (winner != null) {
				System.out.printf("%s wins! %s%n", winner.getHolder(), winnerCondition);
				winner = null;
				round = 0;

				System.out.print("Would you like to play again? (Y/n): ");

				// super magic default Y option
				switch ((input.nextLine().toLowerCase() + 'y').charAt(0)) {
				case 'y':
					continue;
				default:
					return;
				}
			}

			// players turn until they stay or bust
			while (!playerTurn())
				;

			// if the player busted then there's no reason for the dealer to play their turn
			if (winner == null) {
				dealerTurn();
			}

			round++;
		}
	}

}
