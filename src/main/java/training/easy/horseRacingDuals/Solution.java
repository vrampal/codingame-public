package training.easy.horseRacingDuals;

import java.util.*;

class Solution {

	public static void main(String args[]) {
		new Solution().run();
	}
	
	Scanner in = new Scanner(System.in);
	
	void run() {
		int horseCount = in.nextInt();

		int horsePower[] = new int[horseCount];
		for (int horseIdx = 0; horseIdx < horseCount; horseIdx++) {
			horsePower[horseIdx] = in.nextInt();
		}

		Arrays.sort(horsePower);

		int smallestDelta = Integer.MAX_VALUE;
		for (int horseIdx = 1; horseIdx < horseCount; horseIdx++) {
			int delta = horsePower[horseIdx] - horsePower[horseIdx - 1];
			if (smallestDelta > delta) {
				smallestDelta = delta;
			}
		}

		System.out.println(smallestDelta);
	}

}
