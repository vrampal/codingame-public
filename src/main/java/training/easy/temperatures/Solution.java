package training.easy.temperatures;

import java.util.*;

class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int temperCount = in.nextInt(); // the number of temperatures to analyze
		if (in.hasNextLine()) {
			in.nextLine();
		}

		String temperStr = in.nextLine(); // the n temperatures expressed as integers ranging from -273 to 5526
		String[] temperStrArray = temperStr.split(" ");

		int bestTemper = 0;
		int smallestNorm = Integer.MAX_VALUE;

		for (int index = 0; index < temperCount; index++) {
			int temper = Integer.valueOf(temperStrArray[index]);
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
