package training.medium.thereIsNoSpoonEpisode1;

import java.util.*;

class Coord {
	final int x;
	final int y;

	Coord(int col, int row) {
		this.x = col;
		this.y = row;
	}

	public String toString() {
		return x + " " + y;
	}
}

class Board {
	static final char POWER = '0';
	static final Coord NONE = new Coord(-1, -1);

	final int width;
	final int height;
	private final String[] cells;

	Board(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new String[height];
	}
	
	Board(Scanner in) {
		this(in.nextInt(), in.nextInt());
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			cells[rowIdx] = in.next();
		}
	}

	char getCellAt(int col, int row) {
		return cells[row].charAt(col);
	}

	boolean isPowerNode(int col, int row) {
		return getCellAt(col, row) == POWER;
	}

	Coord searchRight(Coord from) {
		for (int colIdx = from.x + 1; colIdx < width; colIdx++) {
			if (isPowerNode(colIdx, from.y)) {
				return new Coord(colIdx, from.y);
			}
		}
		return NONE;
	}

	Coord searchDown(Coord from) {
		for (int rowIdx = from.y + 1; rowIdx < height; rowIdx++) {
			if (isPowerNode(from.x, rowIdx)) {
				return new Coord(from.x, rowIdx);
			}
		}
		return NONE;
	}
}

class Player {

	public static void main(String[] args) {
		new Player().run();
	}

	Scanner in = new Scanner(System.in);
	private Board board;

	void run() {
		board = new Board(in);

		for (int rowIdx = 0; rowIdx < board.height; rowIdx++) {
			for (int colIdx = 0; colIdx < board.width; colIdx++) {
				if (board.isPowerNode(colIdx, rowIdx)) {
					Coord pos = new Coord(colIdx, rowIdx);
					Coord right = board.searchRight(pos);
					Coord down = board.searchDown(pos);

					// Three coordinates: a node, its right neighbor, its bottom neighbor
					System.out.println(pos + " " + right + " " + down);
				}
			}
		}
	}

}
