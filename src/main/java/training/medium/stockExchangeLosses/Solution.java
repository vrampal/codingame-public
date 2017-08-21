package training.medium.stockExchangeLosses;

import java.util.*;

class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int valueCount = in.nextInt();
		int maxLoss = 0;
		if (valueCount > 0) {
			// First value
			int value = in.nextInt();
			System.err.println("Value: " + value);

			int highestValue = value;
			System.err.println("highestValue: " + highestValue);
			int prevValue = value;

			// Next values
			for (int index = 1; index < valueCount; index++) {
				value = in.nextInt();
				System.err.println("Value: " + value);

				if (value < prevValue) {
					int loss = value - highestValue;
					System.err.println("loss: " + loss);
					if (maxLoss > loss) {
						maxLoss = loss;
						System.err.println("maxLoss: " + maxLoss);
					}
				} else if ((value > prevValue) && (highestValue < value)) {
					highestValue = value;
					System.err.println("highestValue: " + highestValue);
				}
				prevValue = value;
			}
		}
		System.out.println(Integer.toString(maxLoss));
	}

}
