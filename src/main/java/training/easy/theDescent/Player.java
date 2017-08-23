package training.easy.theDescent;

import java.util.*;

class Player {
	
	static final int MOUNT_COUNT = 8;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while (true) {
			int maxHeightIndex = 0;
			int maxHeight = in.nextInt();

			for (int mountIndex = 1; mountIndex < MOUNT_COUNT; mountIndex++) {
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
