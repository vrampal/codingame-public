package training.hard.surface;

import java.util.*;

class Coord {
	final int x;
	final int y;

	private Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	Coord(Scanner in) {
		this(in.nextInt(), in.nextInt());
	}

	Coord[] adjacent() {
		return new Coord[] { new Coord(x, y - 1), new Coord(x + 1, y),
			new Coord(x,  y + 1), new Coord(x - 1, y)};
	}
}

class Zone {
	int size = 0;
}

class Board {
	static final char WATER = 'O';

	final int width;
	final int height;

	private final String[] cells;
	private final Zone[][] zones;

	private Board(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new String[height];
		zones = new Zone[height][];
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			zones[rowIdx] = new Zone[width];
		}
	}
	
	Board(Scanner in) {
		this(in.nextInt(), in.nextInt());
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			cells[rowIdx] = in.next();
		}
	}

	private boolean cellExist(Coord pos) {
		return ((pos.x >= 0) && (pos.x < width) && (pos.y >= 0) && (pos.y < height));
	}

	private char getCellAt(Coord pos) {
		return cells[pos.y].charAt(pos.x);
	}
	
	private boolean isWater(Coord pos) {
		return (cellExist(pos) && (getCellAt(pos) == WATER));
	}
	
	Zone getZoneAt(Coord pos) {
		if (!isWater(pos)) {
			return new Zone();
		}
		Zone candidate = zones[pos.y][pos.x];
		if (candidate != null) {
			return candidate;
		}
		return floodFill(pos);
	}

	private Zone floodFill(Coord start) {
		Zone zone = new Zone();
		Queue<Coord> toFill = new ArrayDeque<>();
		toFill.add(start);
		while (!toFill.isEmpty()) {
			Coord pos = toFill.poll();
			if (zones[pos.y][pos.x] == null) {
				zones[pos.y][pos.x] = zone;
				zone.size += 1;
				for (Coord nextPos : pos.adjacent()) {
					if (isWater(nextPos)) {
						// Note: queue may contains duplicates
						toFill.add(nextPos);
					}
				}
			}
		}
		return zone;
	}
}

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		Board board = new Board(in);
		int posCount = in.nextInt();
		for (int posIdx = 0; posIdx < posCount; posIdx++) {
			Coord pos = new Coord(in);
			Zone zone = board.getZoneAt(pos);
			System.out.println(zone.size);
		}
	}

}
