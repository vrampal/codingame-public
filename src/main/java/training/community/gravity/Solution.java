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
		//System.err.println(width + " " + height);
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			String row = in.next();
			//System.err.println(line);
			rows[rowIdx] = new StringBuilder(row);
		}
	}

	char getCellAt(int x, int y) {
		return rows[y].charAt(x);
	}

	void setCellAt(int x, int y, char ch) {
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
				if (board.getCellAt(x, y) == '.') {
					dotCnt++;
				}
			}

			int y;
			for(y = 0; y < dotCnt; y++) {
				board.setCellAt(x, y, '.');
			}
			for (; y < board.height; y++) {
				board.setCellAt(x, y, '#');
			}
		}

		for (int y = 0; y < board.height; y++) {
			System.out.println(board.rows[y]);
		}
	}

}
