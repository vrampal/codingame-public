package training.community.gravity;

import java.util.*;

class Board {
	final int width;
	final int height;
	final StringBuilder[] rows;

	private Board(int width, int height) {
		this.width = width;
		this.height = height;
		//System.err.println(width + " " + height);
		rows = new StringBuilder[height];
	}

	Board(Scanner in) {
		this(in.nextInt(), in.nextInt());
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			String line = in.next();
			//System.err.println(line);
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
			int dotCnt = 0;
			for (int y = 0; y < board.height; y++) {
				if (board.getCell(x, y) == '.') {
					dotCnt++;
				}
			}

			int y;
			for(y = 0; y < dotCnt; y++) {
				board.setCell(x, y, '.');
			}
			for (; y < board.height; y++) {
				board.setCell(x, y, '#');
			}
		}

		for (int y = 0; y < board.height; y++) {
			System.out.println(board.rows[y]);
		}
	}

}
