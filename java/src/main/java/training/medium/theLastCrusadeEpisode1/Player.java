package training.medium.theLastCrusadeEpisode1;

import java.util.*;

enum Direction {
	TOP, RIGHT, BOTTOM, LEFT;
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
		case TOP:    return new Coord(x,     y - 1);
		case RIGHT:  return new Coord(x + 1, y);
		case BOTTOM: return new Coord(x,     y + 1);
		case LEFT:   return new Coord(x - 1, y);
		default:     throw new IllegalArgumentException("Invalid dir: " + dir);
		}
	}

	public String toString() {
		return x + " " + y;
	}
}

class Cell {
	final int type;
	final Direction fromTop;
	final Direction fromLeft;
	final Direction fromRight;
	
	Cell(int type, Direction fromTop, Direction fromLeft, Direction fromRight) {
		this.type = type;
		this.fromTop = fromTop;
		this.fromLeft = fromLeft;
		this.fromRight = fromRight;
	}
	
	Direction cross(Direction in) {
		Direction out;
		if (in == Direction.LEFT) {
			out = fromLeft;
		} else if (in == Direction.RIGHT) {
			out = fromRight;
		} else {
			out = fromTop;
		}
		return out;
	}
}

class Board {
	private static final Cell[] TYPES = {
			// Plain
			new Cell(0, null, null, null),
			// Empty
			new Cell(1, Direction.BOTTOM, Direction.BOTTOM, Direction.BOTTOM),
			// In line
			new Cell(2, null, Direction.RIGHT, Direction.LEFT),
			new Cell(3 ,Direction.BOTTOM, null, null),
			// Double turn
			new Cell(4, Direction.LEFT, null, Direction.BOTTOM),
			new Cell(5 ,Direction.RIGHT, Direction.BOTTOM, null),
			// T shaped
			new Cell(6, null, Direction.RIGHT, Direction.LEFT),
			new Cell(7, Direction.BOTTOM, null, Direction.BOTTOM),
			new Cell(8, null, Direction.BOTTOM, Direction.BOTTOM),
			new Cell(9, Direction.BOTTOM, Direction.BOTTOM, null),
			// Simple turn
			new Cell(10, Direction.LEFT, null, null),
			new Cell(11, Direction.RIGHT, null, null),
			new Cell(12, null, null, Direction.BOTTOM),
			new Cell(13, null, Direction.BOTTOM, null),
	};
	
	final int width;
	final int height;
	private final Cell[][] cells;

	private Board(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new Cell[height][];
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			cells[rowIdx] = new Cell[width];
		}		
	}
	
	Board(Scanner in) {
		this(in.nextInt(), in.nextInt());
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			for (int colIdx = 0; colIdx < width; colIdx++) {
				int n = in.nextInt();
				cells[rowIdx][colIdx] = TYPES[n];
			}
		}
	}

	Cell getCellAt(Coord pos) {
		return cells[pos.y][pos.x];
	}
}

class Player {

	public static void main(String[] args) {
		new Player().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		Board board = new Board(in);
		Coord exitPos = new Coord(in.nextInt(), board.height - 1);

		while (true) {
			Coord posIn = new Coord(in);
			//System.err.println("posIn: " + posIn);
			Direction dirIn = Direction.valueOf(in.next());
			//System.err.println("dirIn: " + dirIn);
			Cell room = board.getCellAt(posIn);
			//System.err.println("roomType: " + room.type);
			Direction dirOut = room.cross(dirIn);
			//System.err.println("dirOut: " + dirOut);
			Coord posOut = posIn.add(dirOut);

			// One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
			System.out.println(posOut);
		}
	}

}
