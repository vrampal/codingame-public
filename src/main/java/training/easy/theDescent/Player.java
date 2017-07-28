package training.easy.theDescent;

import java.util.*;

class Player {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		
		while (true) {
			int maxHeight = -1;
			int maxHeightIndex = -1;

			for (int mountIndex = 0; mountIndex < 8; mountIndex++) {
				int height = in.nextInt(); // represents the height of one mountain.

				if (maxHeight < height) {
					maxHeight = height;
					maxHeightIndex = mountIndex;
				}
			}

			System.out.println(maxHeightIndex); // The index of the mountain to fire on.
		}
	}

}
