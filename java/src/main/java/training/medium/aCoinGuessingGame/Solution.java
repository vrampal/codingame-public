package training.medium.aCoinGuessingGame;

import java.util.*;

// Hello reader, this is my submission for the Feb 2022 event.
// The implementation has a significant memory footprint,
// but it should be easy to understand if you know object-oriented design.

class Association {
	final Collection<Integer> allOddNum = new ArrayList<>();
	final Collection<Integer> allEvenNum = new ArrayList<>();

	// Tracks what associations are possible for any integer
	private final Map<Integer, Collection<Integer>> possible = new HashMap<>();
	
	Association(int coinCount) {
		// Generate all odd and even number once and for all
		for (int index = 0; index < coinCount; index++) {
			allOddNum.add((2 * index) + 1);
			allEvenNum.add((2 * index) + 2);
		}

		for (int num = 1; num <= (2 * coinCount); num++) {
			possible.put(num, new TreeSet<>());
		}
		
		// Set all associations as possible
		for (int odd : allOddNum) {
			for (int even : allEvenNum) {
				possible.get(odd).add(even);
				possible.get(even).add(odd);
			}
		}
	}
	
	int otherSide(int side1) {
		return possible.get(side1).iterator().next();
	}
	
	void remove(int side1, int side2) {
		Collection<Integer> possibleSide2 = possible.get(side1);
		boolean modified = possibleSide2.remove(side2);
		if (modified && (possibleSide2.size() == 1)) {
			cascade(side1);
		}
	}
	
	private void cascade(int side1) {
		int side2 = otherSide(side1);
		possible.put(side2, Arrays.asList(side1));
		for (int other_side1 : possible.keySet()) {
			if (other_side1 != side1) {
				remove(other_side1, side2);
			}
		}
	}
}

class Solution {
	Scanner in = new Scanner(System.in);

	void run() {
		int coinCount = in.nextInt();
		Association association = new Association(coinCount);
		
		int tossCount = in.nextInt();
		for (int tossIndex = 0; tossIndex < tossCount; tossIndex++) {
			Collection<Integer> values = new HashSet<>();
			for (int coinIndex = 0; coinIndex < coinCount; coinIndex++) {
				values.add(in.nextInt());
			}
			// Eliminate impossible associations
			for (int side1 : values) {
				for (int side2 : values) {
					association.remove(side1, side2);
				}
			}
		}
		
		List<String> solutions = new ArrayList<>();
		for (int odd : association.allOddNum) {
			int even = association.otherSide(odd);
			solutions.add(Integer.toString(even));
		}
		System.out.println(String.join(" ", solutions));
	}

	public static void main(String args[]) {
		new Solution().run();
	}
}
