package training.easy.temperatures;

import java.util.*;

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}
	
	Scanner in = new Scanner(System.in);
	
	void run() {
		int temperCount = in.nextInt(); // the number of temperatures to analyse
		int bestTemper = 0;
		int smallestNorm = Integer.MAX_VALUE;

		for (int index = 0; index < temperCount; index++) {
			int temper = in.nextInt(); // a temperature expressed as an integer ranging from -273 to 5526
			int norm = temper * 2;
			if (temper < 0) {
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
