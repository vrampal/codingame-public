package training.medium.winamaxBattle;

import java.util.*;

class Solution {

	public static void main(String args[]) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	public void run() {
		int p1DeckSize = in.nextInt(); // the number of cards for player 1
		Deque<String> deckP1 = new LinkedList<>();
		for (int i = 0; i < p1DeckSize; i++) {
			String cardP1 = in.next(); // the n cards of player 1
			deckP1.add(cardP1);
		}

		int p2DeckSize = in.nextInt(); // the number of cards for player 2
		Deque<String> deckP2 = new LinkedList<>();
		for (int i = 0; i < p2DeckSize; i++) {
			String cardP2 = in.next(); // the m cards of player 2
			deckP2.addLast(cardP2);
		}

		System.err.println("DeckP1: " + printDeck(deckP1));
		System.err.println("DeckP2: " + printDeck(deckP2));

		int roundCount = 0;
		Deque<String> tableP1 = new LinkedList<>();
		Deque<String> tableP2 = new LinkedList<>();
		int discardCount = 0;

		while (!deckP1.isEmpty() && !deckP2.isEmpty()) {
			String cardP1 = deckP1.pollFirst();
			String cardP2 = deckP2.pollFirst();
			tableP1.addFirst(cardP1);
			tableP2.addFirst(cardP2);
			if (discardCount > 0) {
				System.err.println("Discard - " + cardP1 + " / " + cardP2);
				discardCount--;
			} else {
				int delta = compareCards(cardP1, cardP2);
				if (delta > 0) {
					System.err.println("Round for P1 - " + cardP1 + " / " + cardP2);
					transfer(tableP1, deckP1);
					transfer(tableP2, deckP1);
					roundCount++;
				} else if (delta < 0) {
					System.err.println("Round for P2 - " + cardP1 + " / " + cardP2);
					transfer(tableP1, deckP2);
					transfer(tableP2, deckP2);
					roundCount++;
				} else {
					System.err.println("Battle - " + cardP1 + " / " + cardP2);
					discardCount = 3;
				}
			}
		}

		String result = "";
		if (discardCount > 0) {
			result = "PAT";
		} else if (deckP1.isEmpty()) {
			result = "2 " + roundCount;
		} else if (deckP2.isEmpty()) {
			result = "1 " + roundCount;
		}
		System.out.println(result);
	}

	int compareCards(String cardP1, String cardP2) {
		int valP1 = card2val(cardP1);
		int valP2 = card2val(cardP2);
		return valP1 - valP2;
	}

	int card2val(String card) {
		int value;
		if (card.charAt(0) == 'A') {
			value = 14;
		} else if (card.charAt(0) == 'K') {
			value = 13;
		} else if (card.charAt(0) == 'Q') {
			value = 12;
		} else if (card.charAt(0) == 'J') {
			value = 11;
		} else {
			String cardValStr = card.substring(0, card.length() - 1);
			value = Integer.parseInt(cardValStr);
		}
		return value;
	}

	void transfer(Deque<String> from, Deque<String> to) {
		while (!from.isEmpty()) {
			String card = from.pollLast();
			to.addLast(card);
		}
	}

	String printDeck(Deque<String> deck) {
		StringBuilder buff = new StringBuilder();
		for (String card : deck) {
			buff.append(card).append(' ');
		}
		return buff.toString();
	}

}
