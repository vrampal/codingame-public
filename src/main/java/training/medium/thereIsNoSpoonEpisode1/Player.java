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
		//System.err.println(height + " " + width);
		cells = new String[height];
	}
	
	Board(Scanner in) {
		this(in.nextInt(), in.nextInt());
		if (in.hasNextLine()) {
			in.nextLine();
		}
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			String row = in.nextLine();
			//System.err.println(row);
			cells[rowIdx] = row;
		}
	}

	char getCellAt(int col, int row) {
		return cells[row].charAt(col);
	}

	boolean isPowerNode(int col, int row) {
		return getCellAt(col, row) == POWER;
	}

	Coord searchRight(Coord from) {
		for (int col = from.x + 1; col < width; col++) {
			if (isPowerNode(col, from.y)) {
				return new Coord(col, from.y);
			}
		}
		return NONE;
	}

	Coord searchDown(Coord from) {
		for (int row = from.y + 1; row < height; row++) {
			if (isPowerNode(from.x, row)) {
				return new Coord(from.x, row);
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
