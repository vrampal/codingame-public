package global.template;

import static java.lang.Math.*;

import java.util.*;

enum Direction4 {
	N, E, S, W;
	//NORTH, EAST, SOUTH, WEST;
	//UP, RIGHT, DOWN, LEFT;
	//TOP, RIGHT, BOTTOM, LEFT;

	Direction4 cw() {
		return values()[(ordinal() + 1) % 4];
	}

	Direction4 oposite() {
		return values()[(ordinal() + 2) % 4];
	}

	Direction4 ccw() {
		return values()[(ordinal() + 3) % 4];
	}
}

enum Direction6 {
	NE, E, SE, SW, W, NW;

	Direction6 cw() {
		return values()[(ordinal() + 1) % 6];
	}

	Direction6 oposite() {
		return values()[(ordinal() + 3) % 6];
	}

	Direction6 ccw() {
		return values()[(ordinal() + 5) % 6];
	}

	int angle(Direction6 other) {
		int angle = abs(ordinal() - other.ordinal());
		if (angle > 3) {
			angle = 6 - angle;
		}
		return angle;
	}
}

enum Direction8 {
	N, NE, E, SE, S, SW, W, NW;

	Direction8 cw() {
		return values()[(ordinal() + 1) % 8];
	}

	Direction8 oposite() {
		return values()[(ordinal() + 4) % 8];
	}

	Direction8 ccw() {
		return values()[(ordinal() + 7) % 8];
	}

	int angle(Direction8 other) {
		int angle = abs(ordinal() - other.ordinal());
		if (angle > 4) {
			angle = 8 - angle;
		}
		return angle;
	}
}

class Coord {
	final int x;
	final int y;

	Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	Coord(Scanner in) {
		x = in.nextInt();
		y = in.nextInt();
		//System.err.println(toString());
	}

	Coord add(Direction4 dir) {
		switch (dir) {
		case N:  return new Coord(x,     y - 1);
		case E:  return new Coord(x + 1, y);
		case S:  return new Coord(x,     y + 1);
		case W:  return new Coord(x - 1, y);
		default: throw new IllegalArgumentException("Invalid dir: " + dir);
		}
	}

	Coord add(Direction6 dir) {
		if ((y % 2) == 0) {
			// Even lines
			switch (dir) {
			case NE: return new Coord(x,     y - 1);
			case E:  return new Coord(x + 1, y);
			case SE: return new Coord(x,     y + 1);
			case SW: return new Coord(x - 1, y + 1);
			case W:  return new Coord(x - 1, y);
			case NW: return new Coord(x - 1, y - 1);
			default: throw new IllegalArgumentException("Invalid dir: " + dir);
			}
		} else {
			// Odd lines
			switch (dir) {
			case NE: return new Coord(x + 1, y - 1);
			case E:  return new Coord(x + 1, y);
			case SE: return new Coord(x + 1, y + 1);
			case SW: return new Coord(x,     y + 1);
			case W:  return new Coord(x - 1, y);
			case NW: return new Coord(x,     y - 1);
			default: throw new IllegalArgumentException("Invalid dir: " + dir);
			}
		}
	}

	Coord add(Direction8 dir) {
		switch (dir) {
		case N:  return new Coord(x,     y - 1);
		case NE: return new Coord(x + 1, y - 1);
		case E:  return new Coord(x + 1, y);
		case SE: return new Coord(x + 1, y + 1);
		case S:  return new Coord(x,     y + 1);
		case SW: return new Coord(x - 1, y + 1);
		case W:  return new Coord(x - 1, y);
		case NW: return new Coord(x - 1, y - 1);
		default: throw new IllegalArgumentException("Invalid dir: " + dir);
		}
	}

	Coord add(Coord other) {
		return new Coord(x + other.x, y + other.y);
	}

	Coord sub(Coord other) {
		return new Coord(x - other.x, y - other.y);
	}

	Coord mult(double scale) {
		int newX = (int) rint(x * scale);
		int nexY = (int) rint(y * scale);
		return new Coord(newX, nexY);
	}

	// Manhattan distance (for 4 directions maps)
	// see: https://en.wikipedia.org/wiki/Taxicab_geometry
	int distanceMan(Coord other) {
		int deltaX = abs(x - other.x);
		int deltaY = abs(y - other.y);
		return deltaX + deltaY;
	}

	// Chebyshev distance (for 8 directions maps)
	// see: https://en.wikipedia.org/wiki/Chebyshev_distance
	int distanceCheb(Coord other) {
		int deltaX = abs(x - other.x);
		int deltaY = abs(y - other.y);
		return max(deltaX, deltaY);
	}

	// Euclidean distance (for physics engine)
	// https://en.wikipedia.org/wiki/Euclidean_distance
	double distanceEcl(Coord other) {
		int deltaX = x - other.x;
		int deltaY = y - other.y;
		return sqrt(((double)deltaX * deltaX) + ((double)deltaY * deltaY));
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + x;
		result = PRIME * result + y;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Coord other = (Coord) obj;
		return (x == other.x) && (y == other.y);
	}

	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}

class Segment {
	final Coord from;
	final Coord to;

	Segment(Coord from, Coord to) {
		this.from = from;
		this.to = to;
	}

	Coord middle() {
		int newX = (from.x + to.x) / 2;
		int newY = (from.y + to.y) / 2;
		return new Coord(newX, newY);
	}

	/**
	 * Check if a point is inside a bounding box.
	 * @return true if and only if inside.
	 */
	boolean inBoundingBox(Coord pos) {
		return min(from.x, to.x) <= pos.x && pos.x <= max(from.x, to.x)
				&& min(from.y, to.y) <= pos.y && pos.y <= max(from.y, to.y);
	}

	/**
	 * Find the orientation of the triplet (from, to, pos).
	 * @return 1 when clockwise, 0 when colinear, -1 when counterclockwise.
	 */
	int orientation(Coord pos) {
		int val = (to.y - from.y) * (pos.x - to.x) - (to.x - from.x) * (pos.y - to.y);
		return (val > 0) ? 1 : ((val < 0) ? -1 : 0);
	}

	/**
	 * Test of 2 segments intersects.
	 * @return true if and only if segments intersects.
	 */
	boolean intersect(Segment other) {
		int o1 = orientation(other.from);
		int o2 = orientation(other.to);
		int o3 = other.orientation(from);
		int o4 = other.orientation(to);
		return (o1 != o2 && o3 != o4) // <- General case, special case below
				|| (o1 == 0 && inBoundingBox(other.from)) || (o2 == 0 && inBoundingBox(other.to))
				|| (o3 == 0 && other.inBoundingBox(from)) || (o4 == 0 && other.inBoundingBox(to));
	}

	public String toString() {
		return from + "-" + to;
	}
}

class Board {
	final int width;
	final int height;
	private final StringBuilder[] cells;

	Board(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new StringBuilder[height];
	}

	Board(Scanner in) {
		this(in.nextInt(), in.nextInt());
		//System.err.println(height + " " + width);
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			String row = in.next();
			//System.err.println(row);
			cells[rowIdx] = new StringBuilder(row);
		}
	}

	boolean cellExist(Coord pos) {
		return ((pos.y >= 0) && (pos.y < height) && (pos.x >= 0) && (pos.x < width));
	}

	private char getCellAt(int row, int col) {
		return cells[row].charAt(col);
	}

	char getCellAt(Coord pos) {
		return getCellAt(pos.y, pos.x);
	}

	void setCellAt(Coord pos, char val) {
		cells[pos.y].setCharAt(pos.x, val);
	}

	Coord findFirst(char target) {
		for (int colIdx = 0; colIdx < width; colIdx++) {
			for (int rowIdx = 0; rowIdx < height; rowIdx++) {
				char val = cells[rowIdx].charAt(colIdx);
				if (val == target) {
					return new Coord(colIdx, rowIdx);
				}
			}
		}
		return null;
	}
}
