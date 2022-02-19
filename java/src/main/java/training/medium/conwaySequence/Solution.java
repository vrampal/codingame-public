package training.medium.conwaySequence;

import java.util.*;

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}
	
	Scanner in = new Scanner(System.in);
	
	void run() {
		int init = in.nextInt();
		int rank = in.nextInt();

		String conwaySeq = Integer.toString(init);
		while (rank > 1) {
			conwaySeq = next(conwaySeq);
			rank--;
		}

		System.out.println(conwaySeq);
	}

	String next(String sequence) {
		StringBuilder newSequence = new StringBuilder();

		String[] seqSplit = sequence.split(" ");

		String prevElem = seqSplit[0];
		int elemCount = 1;

		for (int elemIdx = 1; elemIdx < seqSplit.length; elemIdx++) {
			String elem = seqSplit[elemIdx];
			if (elem.equals(prevElem)) {
				elemCount++;
			} else {
				newSequence.append(elemCount).append(' ');
				newSequence.append(prevElem).append(' ');
				prevElem = elem;
				elemCount = 1;
			}
		}
		newSequence.append(elemCount).append(' ');
		newSequence.append(prevElem);

		return newSequence.toString();
	}

}
