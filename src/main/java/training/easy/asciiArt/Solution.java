package training.easy.asciiArt;

import java.util.*;

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);
	private int charWidth;

	void run() {
		charWidth = in.nextInt();
		int charHeight = in.nextInt();
		in.nextLine(); // Eat \n before reading raw lines
		String textIn = in.nextLine();
		for (int lineIdx = 0; lineIdx < charHeight; lineIdx++) {
			String template = in.nextLine();
			processLine(textIn, template);
		}
	}

	private void processLine(String textIn, String template) {
		int inLength = textIn.length();
		StringBuilder buffOut = new StringBuilder(inLength * charWidth);
		
		for (int charIdx = 0; charIdx < inLength; charIdx++) {
			char ch = textIn.charAt(charIdx);

			int charNum = char2num(ch);
			String element = template.substring(charNum * charWidth, (charNum + 1) * charWidth);
			buffOut.append(element);
		}
		
		System.out.println(buffOut);
	}

	private int char2num(char ch) {
		int val = 26;
		if ((ch >= 'a') && (ch <= 'z')) {
			val = ch - 'a';
		} else  if ((ch >= 'A') && (ch <= 'Z')) {
			val = ch - 'A';
		}
		return val;
	}

}
