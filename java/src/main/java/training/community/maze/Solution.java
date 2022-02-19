package training.community.maze;

import java.util.*;

enum Direction {
	N, E, S, W;
}

class Coord {
	final int x;
	final int y;

	Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	Coord(Scanner in) {
		this(in.nextInt(), in.nextInt());
	}

	Coord add(Direction dir) {
		switch (dir) {
		case N:  return new Coord(x,     y - 1);
		case E:  return new Coord(x + 1, y);
		case S:  return new Coord(x,     y + 1);
		case W:  return new Coord(x - 1, y);
		default: throw new IllegalArgumentException("Invalid dir: " + dir);
		}
	}

	public String toString() {
		return x + " " + y;
	}
}

class Board {
	static final char WALL = '#';
	static final char EMPTY = '.';
	static final char MARKED = 'o';

	final int width;
	final int height;
	private final StringBuilder[] cells;

	private Board(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new StringBuilder[height];
	}

	Board(Scanner in) {
		this(in.nextInt(), in.nextInt());
	}

	void readFrom(Scanner in) {
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			String row = in.next();
			cells[rowIdx] = new StringBuilder(row);
		}
	}

	boolean cellExist(Coord pos) {
		return ((pos.y >= 0) && (pos.y < height) && (pos.x >= 0) && (pos.x < width));
	}

	char getCellAt(Coord pos) {
		return cells[pos.y].charAt(pos.x);
	}

	void setCellAt(Coord pos, char val) {
		cells[pos.y].setCharAt(pos.x, val);
	}

	void floodFill(Coord start) {
		Queue<Coord> toFill = new ArrayDeque<>();
		toFill.add(start);
		while (!toFill.isEmpty()) {
			Coord pos = toFill.poll();
			if (getCellAt(pos) == EMPTY) {
				setCellAt(pos, MARKED);
				for (Direction dir : Direction.values()) {
					Coord pos2 = pos.add(dir);
					if (cellExist(pos2)) {
						// Note: queue may contains duplicates
						toFill.add(pos2);
					}
				}
			}
		}
	}

	void debugPrint() {
		for (StringBuilder row : cells) {
			System.err.println(row);
		}
	}
}

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		Board board = new Board(in);
		Coord entry = new Coord(in);
		board.readFrom(in);

		board.floodFill(entry);
		//System.err.println("After fill");
		//board.debugPrint();

		List<Coord> exitList = new ArrayList<>();
		for (int rowIdx = 0; rowIdx < board.height; rowIdx++) {
			Coord pos = new Coord(0, rowIdx);
			if (board.getCellAt(pos) == Board.MARKED) {
				exitList.add(pos);
			}
		}
		for (int colIdx = 1; colIdx < board.width; colIdx++) {
			Coord pos = new Coord(colIdx, 0);
			if (board.getCellAt(pos) == Board.MARKED) {
				exitList.add(pos);
			}
			pos = new Coord(colIdx, board.width - 1);
			if (board.getCellAt(pos) == Board.MARKED) {
				exitList.add(pos);
			}
		}
		for (int rowIdx = 1; rowIdx < board.height; rowIdx++) {
			Coord pos = new Coord(board.width - 1, rowIdx);
			if (board.getCellAt(pos) == Board.MARKED) {
				exitList.add(pos);
			}
		}

		System.out.println(exitList.size());
		for (Coord exit : exitList) {
			System.out.println(exit);
		}
	}

}
