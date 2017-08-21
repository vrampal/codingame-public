package training.medium.networkCabling;

import static java.lang.Math.*;

import java.util.*;

class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int batCnt = in.nextInt();
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int[] batX = new int[batCnt];
		int[] batY = new int[batCnt];
		int[] temp = new int[batCnt];
		for (int batIdx = 0; batIdx < batCnt; batIdx++) {
			int x = in.nextInt();
			minX = min(x, minX);
			maxX = max(x, maxX);
			batX[batIdx] = x;
			int y = in.nextInt();
			batY[batIdx] = y;
			temp[batIdx] = y;
		}
		
		Arrays.sort(temp);
		int medianY = temp[batCnt/2];
		
		long result = maxX - minX;
		for (int batIdx = 0; batIdx < batCnt; batIdx++) {
			result += abs(batY[batIdx] - medianY);
		}
		
		System.out.println(result);
	}

}
