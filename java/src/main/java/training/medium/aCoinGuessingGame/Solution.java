package training.medium.aCoinGuessingGame;

import java.util.*;

// Hello reader, this is my submission for the Feb 2022 event.
// The implementation has a significant memory footprint,
// but it should be easy to understand if you know object-oriented design.

class Coin implements Comparable<Coin> {
	final int oddSide;
	final int evenSide;
	
	Coin(int oddSide, int evenSide) {
		this.oddSide = oddSide;
		this.evenSide = evenSide;
	}

	public int compareTo(Coin other) {
		int val = Integer.compare(oddSide, other.oddSide);
		if (val != 0) {
			return val;
		}
		return Integer.compare(evenSide, other.evenSide);
	}

	// Provided only for debug purpose
	public String toString() {
		return Integer.toString(oddSide) + '-' + evenSide;
	}
}

class Association {
	final int maxNum;
	final Collection<Integer> allOddNum = new ArrayList<>();
	final Collection<Integer> allEvenNum = new ArrayList<>();

	// Tracks what associations are possible for any integer
	private final Map<Integer, Collection<Coin>> possible = new TreeMap<>();
	
	Association(int coinCount) {
		this.maxNum = 2 * coinCount;

		// Generate all odd and even number once and for all
		for (int index = 0; index < coinCount; index++) {
			allOddNum.add((2 * index) + 1);
			allEvenNum.add((2 * index) + 2);
		}

		for (int num = 1; num <= maxNum; num++) {
			possible.put(num, new TreeSet<>());
		}
		
		// Set all associations as possible
		for (int odd : allOddNum) {
			for (int even : allEvenNum) {
				add(new Coin(odd, even));
			}
		}
	}
	
	private void add(Coin coin) {
		possible.get(coin.oddSide).add(coin);
		possible.get(coin.evenSide).add(coin);
	}
	
	void remove(Coin coin) {
		possible.get(coin.oddSide).remove(coin);
		possible.get(coin.evenSide).remove(coin);
	}
	
	private boolean isKnown(int number) {
		return (possible.get(number).size() == 1);
	}
	
	Coin getKnown(int number) {
		return possible.get(number).iterator().next();
	}
	
	// Remove impossible associations when a coin is known
	private void lockCoin(Coin knownCoin) {
		for (int odd : allOddNum) {
			if (odd != knownCoin.oddSide) {
				Coin coin = new Coin(odd, knownCoin.evenSide);
				remove(coin);
			}
		}
		for (int even : allEvenNum) {
			if (even != knownCoin.evenSide) {
				Coin coin = new Coin(knownCoin.oddSide, even);
				remove(coin);
			}
		}
	}
	
	void resolve() {
		boolean allKnown = false;
		while (!allKnown) {
			allKnown = true;
			for (int index = 1; index <= maxNum; index++) {
				if (isKnown(index)) {
					Coin coin = getKnown(index);
					lockCoin(coin);
				} else {
					allKnown = false;
				}
			}
		}
	}
}

class Solution {

	Scanner in = new Scanner(System.in);

	void run() {
		int coinCount = in.nextInt();
		
		Association assos = new Association(coinCount);
		
		int tossCount = in.nextInt();
		for (int tossIndex = 0; tossIndex < tossCount; tossIndex++) {
			Collection<Integer> visibleOdd = new HashSet<>();
			Collection<Integer> visibleEven = new HashSet<>();
			for (int coinIndex = 0; coinIndex < coinCount; coinIndex++) {
				int value = in.nextInt();
				if ((value % 2) == 0) {
					visibleEven.add(value);
				} else {
					visibleOdd.add(value);
				}
			}
			// Eliminate impossible associations
			for (int odd : visibleOdd) {
				for (int even : visibleEven) {
					Coin coin = new Coin(odd, even);
					assos.remove(coin);
				}
			}
		}
		
		assos.resolve();
		
		List<String> solutions = new ArrayList<>();
		for (int odd : assos.allOddNum) {
			Coin coin = assos.getKnown(odd);
			solutions.add(Integer.toString(coin.evenSide));
		}
		System.out.println(String.join(" ", solutions));
	}

	public static void main(String args[]) {
		new Solution().run();
	}

}
