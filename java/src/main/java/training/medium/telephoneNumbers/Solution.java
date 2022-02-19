package training.medium.telephoneNumbers;

import java.util.*;

class LexiNode {
	Map<Character, LexiNode> subNodesByLetter = Collections.emptyMap();

	void addSubNode(char ch, LexiNode node)  {
		if (subNodesByLetter.isEmpty()) {
			subNodesByLetter = new HashMap<>();
		}
		subNodesByLetter.put(ch, node);
	}
}

class LexiTree {
	final LexiNode root = new LexiNode();

	int nodeCount = 0;

	LexiNode get(String word) {
		LexiNode currNode = root;
		for (int charIdx = 0; ((charIdx < word.length()) && (currNode != null)); charIdx++) {
			char ch = word.charAt(charIdx);
			currNode = currNode.subNodesByLetter.get(ch);
		}
		return currNode;
	}

	LexiNode put(String word) {
		LexiNode curNode = root;
		for (int charIdx = 0; charIdx < word.length(); charIdx++) {
			char ch = word.charAt(charIdx);
			LexiNode nextNode = curNode.subNodesByLetter.get(ch);
			if (nextNode == null) {
				nextNode = new LexiNode();
				curNode.addSubNode(ch, nextNode);
				nodeCount++;
			}
			curNode = nextNode;
		}
		return curNode;
	}
}

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		LexiTree lexiTree = new LexiTree();

		int telCount = in.nextInt();
		for (int i = 0; i < telCount; i++) {
			String telWord = in.next();
			lexiTree.put(telWord);
		}

		// The number of elements (referencing a number) stored in the structure.
		System.out.println(lexiTree.nodeCount);
	}

}
