package global.gameCapture;

import java.util.*;

class Player {

	static final int IGNORE_FIRST_COUNT = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < IGNORE_FIRST_COUNT; i++) {
			in.nextLine();
		}
		while (in.hasNextLine()) {
			System.err.println(in.nextLine());
		}
	}

}
