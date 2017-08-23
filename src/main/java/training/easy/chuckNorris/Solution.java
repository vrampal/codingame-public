package training.easy.chuckNorris;

import static java.lang.Math.*;

import java.util.*;

class Splitter {
	static final int OCTAL_MASK = 0x040;

	final String message;

	private int charIdx = 0;
	private int mask = OCTAL_MASK;

	Splitter(String message) {
		this.message = message;
	}

	boolean hasNextBit() {
		return (charIdx < message.length());
	}

	int getNextBit() {
		char ch = message.charAt(charIdx);
		int bit = min(ch & mask, 1);
		mask >>= 1;
		if (mask == 0) {
			charIdx++;
			mask = OCTAL_MASK;
		}
		return bit;
	}
}

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		String message = in.nextLine();
		//System.err.println(message);
		Splitter splitter = new Splitter(message);
		StringBuilder buffOut = new StringBuilder();
		encode(splitter, buffOut);
		System.out.println(buffOut);
	}

	void encode(Splitter splitter, StringBuilder buffOut) {
		int prevBit = -1;
		int count = 0;
		while (splitter.hasNextBit()) {
			int nextBit = splitter.getNextBit();
			if (nextBit != prevBit) {
				sendCount(count, buffOut);
				sendType(nextBit, buffOut);
				prevBit = nextBit;
				count = 1;
			} else {
				count++;
			}
		}
		sendCount(count, buffOut);
		buffOut.deleteCharAt(buffOut.length() - 1);
	}

	private void sendType(int nextBit, StringBuilder buffOut) {
		if (nextBit == 1) {
			buffOut.append("0 ");
		} else {
			buffOut.append("00 ");
		}
	}

	private void sendCount(int count, StringBuilder buffOut) {
		if (count > 0) {
			while (count > 0) {
				buffOut.append('0');
				count--;
			}
			buffOut.append(' ');
		}
	}

}
