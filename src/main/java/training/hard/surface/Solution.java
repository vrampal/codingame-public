package training.hard.surface;

import java.util.*;

enum Direction {
	N, E, S, W;
}

class Coord {
	final int x;
	final int y;

	private Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	Coord(Scanner in) {
		x = in.nextInt();
		y = in.nextInt();
		//System.err.println(toString());
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

class Zone {
	int size = 0;
}

class Board {
	static final char WATER = 'O';
	static final Zone NONE = new Zone();

	final int width;
	final int height;

	private final String[] cells;
	private final Zone[][] zones;

	private Board(int width, int height) {
		this.width = width;
		this.height = height;
		//System.err.println(height + " " + width);
		cells = new String[height];
		zones = new Zone[height][]; 
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			zones[rowIdx] = new Zone[width];
		}
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

	boolean cellExist(Coord pos) {
		return ((pos.y >= 0) && (pos.y < height) && (pos.x >= 0) && (pos.x < width));
	}

	private char getCellAt(Coord pos) {
		return cells[pos.y].charAt(pos.x);
	}
	
	boolean isWater(Coord pos) {
		return (getCellAt(pos) == WATER);
	}
	
	Zone getZoneAt(Coord pos) {
		if (!isWater(pos)) {
			return NONE;
		}
		Zone zone = zones[pos.y][pos.x];
		if (zone != null) {
			return zone;
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
				zone.size++;
				for (Direction dir : Direction.values()) {
					Coord pos2 = pos.add(dir);
					if (cellExist(pos2) && isWater(pos2)) {
						// Note: queue may contains duplicates
						toFill.add(pos2);
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
