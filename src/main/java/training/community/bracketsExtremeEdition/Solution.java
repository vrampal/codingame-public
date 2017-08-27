package training.community.bracketsExtremeEdition;

import java.util.*;

class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String expression = in.next();

		Deque<Character> queue = new ArrayDeque<>();
		for (int idx = 0; idx < expression.length(); idx++) {
			char ch = expression.charAt(idx);
			switch (ch) {
			case '(':
			case '[':
			case '{':
				queue.addLast(ch);
				break;
			case ')':
				if ((queue.isEmpty()) || (queue.pollLast() != '(')) {
					System.out.println("false");
					return;
				}
				break;
			case ']':
				if ((queue.isEmpty()) || (queue.pollLast() != '[')) {
					System.out.println("false");
					return;
				}
				break;
			case '}':
				if ((queue.isEmpty()) || (queue.pollLast() != '{')) {
					System.out.println("false");
					return;
				}
				break;
			}
		}
		System.out.println(queue.isEmpty());
	}

}
