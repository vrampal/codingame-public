package training.community.theFastest;

import java.util.*;

class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String[] val = new String[n];
		for (int i = 0; i < n; i++) {
			val[i] = in.next();
		}
		Arrays.sort(val);
		System.out.println(val[0]);
	}

}
