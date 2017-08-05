package training.medium.theGift;

import static java.lang.Math.*;

import java.util.*;

class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int nbOods = in.nextInt();
		int cost = in.nextInt();

		int totalBudget = 0;
		int[] budgets = new int[nbOods];
		for (int index = 0; index < nbOods; index++) {
			int budget = in.nextInt();
			budgets[index] = budget;
			totalBudget += budget;
		}

		if (cost > totalBudget) {
			System.out.println("IMPOSSIBLE");
			return;
		}

		Arrays.sort(budgets);
		StringBuilder result = new StringBuilder();
		int remainCost = cost;
		for (int index = 0; index < nbOods; index++) {
			int remainOods = nbOods - index;
			int remainAvg = remainCost / remainOods;
			int val = min(budgets[index], remainAvg);
			remainCost -= val;
			result.append(val).append('\n');
		}
		
		System.out.println(result);
	}

}
