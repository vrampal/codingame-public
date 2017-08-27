package global.template;

import static java.lang.Math.*;

import java.util.*;

enum Direction4 {
	N, E, S, W; // TODO control name and order
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
	NE, E, SE, SW, W, NW; // TODO control name and order

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
	N, NE, E, SE, S, SW, W, NW; // TODO control name and order

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
		this(in.nextInt(), in.nextInt());
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

	// Manhattan distance (for 4 directions maps)
	// see: https://en.wikipedia.org/wiki/Taxicab_geometry
	int distanceMan(Coord other) {
		int deltaX = abs(x - other.x);
		int deltaY = abs(y - other.y);
		return deltaX + deltaY;
	}

	CubeCoord toCubeCoord() {
		int newX = x - (y - (y & 1)) / 2;
		int newZ = y;
		int newY = -(newX + newZ);
		return new CubeCoord(newX, newY, newZ);
	}

	// Hexagonal distance (for 6 direction maps)
	// http://www.redblobgames.com/grids/hexagons/
	int distanceHexa(Coord other) {
		return toCubeCoord().distanceHexa(other.toCubeCoord());
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
		//return x + " " + y; // TODO change if you use Coord in System.out
		return "[" + x + ", " + y + "]";
	}
}

class CubeCoord {
	final int x;
	final int y;
	final int z;

	public CubeCoord(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	Coord toOffsetCoord() {
		int newX = x + (z - (z & 1)) / 2;
		int newY = z;
		return new Coord(newX, newY);
	}

	CubeCoord add(Direction6 dir) {
		switch (dir) {
		case NE: return new CubeCoord(x + 1, y,     z - 1);
		case E:  return new CubeCoord(x + 1, y - 1, z);
		case SE: return new CubeCoord(x,     y - 1, z + 1);
		case SW: return new CubeCoord(x - 1, y,     z + 1);
		case W:  return new CubeCoord(x - 1, y + 1, z);
		case NW: return new CubeCoord(x,     y + 1, z - 1);
		default: throw new IllegalArgumentException("Invalid dir: " + dir);
		}
	}

	int distanceHexa(CubeCoord other) {
		int deltaX = abs(x - other.x);
		int deltaY = abs(y - other.y);
		int deltaZ = abs(z - other.z);
		return (deltaX + deltaY + deltaZ) / 2;
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + x;
		result = PRIME * result + y;
		result = PRIME * result + x;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		CubeCoord other = (CubeCoord) obj;
		return (x == other.x) && (y == other.y) && (z == other.z);
	}

	public String toString() {
		return "[" + x + ",  " + y +  ",  " + z + "]";
	}
}

class Segment {
	final Coord from;
	final Coord to;

	Segment(Coord from, Coord to) {
		this.from = from;
		this.to = to;
	}

	// Check if a point is inside a bounding box
	boolean inBoundingBox(Coord pos) {
		return min(from.x, to.x) <= pos.x && pos.x <= max(from.x, to.x)
				&& min(from.y, to.y) <= pos.y && pos.y <= max(from.y, to.y);
	}

	// Find the orientation of the triplet (from, to, pos)
	// Returns 1 when clockwise, 0 when colinear, -1 when counterclockwise
	int orientation(Coord pos) {
		int val = (to.y - from.y) * (pos.x - to.x) - (to.x - from.x) * (pos.y - to.y);
		return (val > 0) ? 1 : ((val < 0) ? -1 : 0);
	}

	// Test if 2 segments intersects
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

class Zone {
	final Set<Coord> coords = new HashSet<>();

	int size() {
		return coords.size();
	}
}

class Board {
	final int width;
	final int height;
	private final StringBuilder[] cells;
	private final Zone[][] zones;

	private Board(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new StringBuilder[height];
		zones = new Zone[height][];
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			zones[rowIdx] = new Zone[width];
		}
	}

	Board(Scanner in) {
		this(in.nextInt(), in.nextInt());
		// TODO split here is content is not right after size
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			String row = in.next(); // TODO use in.nextLine() if the line contains spaces
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
				char val = getCellAt(rowIdx, colIdx);
				if (val == target) {
					return new Coord(colIdx, rowIdx);
				}
			}
		}
		return null;
	}

	int count(char target) {
		int count = 0;
		for (int colIdx = 0; colIdx < width; colIdx++) {
			for (int rowIdx = 0; rowIdx < height; rowIdx++) {
				char val = getCellAt(rowIdx, colIdx);
				if (val == target) {
					count++;
				}
			}
		}
		return count;
	}

	boolean canWalkOn(Coord pos) {
		return (cellExist(pos) && (getCellAt(pos) != '#')); // TODO modify depending of the game
	}

	void clearZones() {
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			for (int colIdx = 0; colIdx < width; colIdx++) {
				zones[rowIdx][colIdx] = null;
			}
		}
	}

	Zone getZoneAt(Coord pos) {
		if (!canWalkOn(pos)) {
			return new Zone();
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
				zone.coords.add(pos);
				for (Direction4 dir : Direction4.values()) { // TODO modify depending of the game
					Coord nextPos = pos.add(dir);
					if (canWalkOn(nextPos)) {
						// Note: queue may contains duplicates
						toFill.add(nextPos);
					}
				}
			}
		}
		return zone;
	}

	void debugPrint() {
		for (StringBuilder row : cells) {
			System.err.println(row);
		}
	}
}
