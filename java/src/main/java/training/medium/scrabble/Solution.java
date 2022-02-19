package training.medium.scrabble;

import java.util.*;

class Record {
	static final int[] CHAR_VAL = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };
	static final Record NONE = new Record("", 0);

	final String word;
	final int score;
	final int index;

	Record(String word, int index) {
		this.word = word;
		this.score = evalScore(word);
		this.index = index;
	}

	static int evalScore(String letters) {
		int score = 0;
		for (int charIdx = 0; charIdx < letters.length(); charIdx++) {
			char ch = letters.charAt(charIdx);
			score += charValue(ch);
		}
		return score;
	}

	static int charValue(char ch) {
		if ((ch >= 'a') && (ch <= 'z')) {
			return CHAR_VAL[ch - 'a'];
		}
		return 0;
	}
	
	boolean betterThan(Record other) {
		return ((score > other.score) || ((score ==  other.score) && (index < other.index)));
	}
}

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		int wordCount = in.nextInt();
		Map<String, Record> dictionary = new HashMap<>(wordCount * 2);
		for (int wordIdx = 0; wordIdx < wordCount; wordIdx++) {
			String word = in.next();
			String sortLetters = sortLetters(word);
			//System.err.println("Adding " + word + " -> " + sortLetters);
			Record actual = dictionary.get(sortLetters);
			if (actual == null) {
				Record record = new Record(word, wordIdx);
				dictionary.put(sortLetters, record);
			}
		}
		String rawLetters = in.next();
		String sortLetters = sortLetters(rawLetters);
		//System.err.println("Input " + rawLetters + " -> " + sortLetters);

		Record bestNode = Record.NONE;
		Collection<String> candidates = new HashSet<>();
		candidates.add(sortLetters);
		for (int i = 0; i < sortLetters.length(); i++) {
			for (String word : candidates) {
				Record node = dictionary.get(word);
				if ((node != null) && (node.betterThan(bestNode))) {
					bestNode = node;
				}
			}
			candidates = shorterWords(candidates);
		}

		System.out.println(bestNode.word);
	}

	String sortLetters(String letters) {
		char[] charArray = new char[letters.length()];
		letters.getChars(0, letters.length(), charArray, 0);
		Arrays.sort(charArray);
		return new String(charArray, 0, charArray.length);
	}

	Collection<String> shorterWords(Collection<String> wordSet) {
		Collection<String> newWordSet = new HashSet<>();
		for (String word : wordSet) {
			String newWord = word.substring(0, word.length() - 1);
			newWordSet.add(newWord);
			for (int charIdx = 1; charIdx < word.length(); charIdx++) {
				newWord = word.substring(0, charIdx - 1) + word.substring(charIdx);
				newWordSet.add(newWord);
			}
		}
		return newWordSet;
	}

}
