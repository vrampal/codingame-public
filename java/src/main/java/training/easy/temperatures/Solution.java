package training.easy.temperatures;

import java.util.*;

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}
	
	Scanner in = new Scanner(System.in);
	
	void run() {
		int temperCount = in.nextInt(); // the number of temperatures to analyze

		int bestTemper = 0;
		int smallestNorm = Integer.MAX_VALUE;

		for (int index = 0; index < temperCount; index++) {
			int temper = in.nextInt();
			int norm;
			if (temper > 0) {
				norm = temper * 2;
			} else {
				norm = (temper * -2) + 1;
			}

			if (smallestNorm > norm) {
				smallestNorm = norm;
				bestTemper = temper;
			}
		}

		System.out.println(bestTemper);
	}

}
