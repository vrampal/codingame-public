package training.medium.stockExchangeLosses;

import static java.lang.Math.*;

import java.util.*;

class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int valueCount = in.nextInt();
		int maxLoss = 0;
		if (valueCount > 0) {
			// First value
			int highestValue = in.nextInt();

			// Next values
			for (int index = 1; index < valueCount; index++) {
				int value = in.nextInt();
				highestValue = max(highestValue, value);
				int loss = value - highestValue;
				maxLoss = min(maxLoss, loss);
			}
		}
		System.out.println(Integer.toString(maxLoss));
	}

}
