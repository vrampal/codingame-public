package training.community.gravity;

import java.util.*;

class Board {
	final int width;
	final int height;
	final StringBuilder[] rows;

	private Board(int width, int height) {
		this.width = width;
		this.height = height;
		rows = new StringBuilder[height];
	}

	Board(Scanner in) {
		this(in.nextInt(), in.nextInt());
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			String line = in.next();
			rows[rowIdx] = new StringBuilder(line);
		}
	}

	char getCell(int x, int y) {
		return rows[y].charAt(x);
	}

	void setCell(int x, int y, char ch) {
		rows[y].setCharAt(x, ch);
	}
}

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		Board board = new Board(in);

		for (int x = 0; x < board.width; x++) {
			int dot = 0;
			int sharp = 0;
			for (int y = 0; y < board.height; y++) {
				char ch = board.getCell(x, y);
				if (ch == '.') {
					dot++;
				} else if (ch == '#') {
					sharp++;
				}
			}
			int y = 0;
			while (dot > 0) {
				board.setCell(x, y, '.');
				y++;
				dot--;
			}
			while (sharp > 0) {
				board.setCell(x, y, '#');
				y++;
				sharp--;
			}
		}

		for (int y = 0; y < board.height; y++) {
			System.out.println(board.rows[y]);
		}
	}

}
