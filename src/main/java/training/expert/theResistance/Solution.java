package training.expert.theResistance;

import java.util.*;

class LexiNode {
	LexiNode dot = null;
	LexiNode dash = null;
	int validWords = 0;

	LexiNode getNode(char ch) {
		if (ch == '.') return dot;
		if (ch == '-') return dash;
		throw new IllegalArgumentException("Invalid character: " + ch);
	}

	LexiNode createNode(char ch) {
		if (ch == '.') {
			if (dot == null) {
				dot = new LexiNode();
			}
			return dot;
		}
		if (ch == '-') {
			if (dash == null) {
				dash = new LexiNode();
			}
			return dash;
		}
		throw new IllegalArgumentException("Invalid character: " + ch);
	}
}

class LexiTree {
	private final LexiNode root = new LexiNode();
	
	String encode(String word) {
		StringBuilder builder = new StringBuilder();
		for (int charIdx = 0; charIdx < word.length(); charIdx++) {
			char ch = word.charAt(charIdx);
			switch (ch) {
			case 'A': builder.append(".-"); break;
			case 'B': builder.append("-..."); break;
			case 'C': builder.append("-.-."); break;
			case 'D': builder.append("-.."); break;
			case 'E': builder.append("."); break;
			case 'F': builder.append("..-."); break;
			case 'G': builder.append("--."); break;
			case 'H': builder.append("...."); break;
			case 'I': builder.append(".."); break;
			case 'J': builder.append(".---"); break;
			case 'K': builder.append("-.-"); break;
			case 'L': builder.append(".-.."); break;
			case 'M': builder.append("--"); break;
			case 'N': builder.append("-."); break;
			case 'O': builder.append("---"); break;
			case 'P': builder.append(".--."); break;
			case 'Q': builder.append("--.-"); break;
			case 'R': builder.append(".-."); break;
			case 'S': builder.append("..."); break;
			case 'T': builder.append("-"); break;
			case 'U': builder.append("..-"); break;
			case 'V': builder.append("...-"); break;
			case 'W': builder.append(".--"); break;
			case 'X': builder.append("-..-"); break;
			case 'Y': builder.append("-.--"); break;
			case 'Z': builder.append("--.."); break;
			default: throw new IllegalArgumentException("Invalid character: " + ch);
			}
		}
		return builder.toString();
	}

	void add(String word) {
		String wordMorse = encode(word);
		//System.err.println(word + " -> " + wordMorse);
		LexiNode node = root;
		for (int charIdx = 0; charIdx < wordMorse.length(); charIdx++) {
			char ch = wordMorse.charAt(charIdx);
			node = node.createNode(ch);
		}
		node.validWords++;
	}

	long decode(String encoded) {
		long[] intermediate = new long[encoded.length()];
		decode(encoded, intermediate, 0, 1);
		for (int startIdx = 1; startIdx < encoded.length(); startIdx++) {
			long base = intermediate[startIdx - 1];
			if (base > 0) {
				decode(encoded, intermediate, startIdx, base);
			}
		}
		return intermediate[intermediate.length - 1];
	}

	private void decode(String encoded, long[] intermediate, int startIdx, long base) {
		LexiNode node = root;
		for (int charIdx = startIdx; charIdx < encoded.length(); charIdx++) {
			char ch = encoded.charAt(charIdx);
			node = node.getNode(ch);
			if (node == null) {
				return;
			}
			if (node.validWords > 0) {
				intermediate[charIdx] += base * node.validWords;
			}
		}
	}
}

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		LexiTree lexiTree = new LexiTree();
		String encoded = in.next();
		//System.err.println(encoded);

		int wordCnt = in.nextInt();
		for (int wordIdx = 0; wordIdx < wordCnt; wordIdx++) {
			String word = in.next();
			lexiTree.add(word);
		}

		long res = lexiTree.decode(encoded);
		System.out.println(res);
	}

}
