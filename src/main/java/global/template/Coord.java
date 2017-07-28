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

	private Coord(int x, int y) {
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
		case N: return new Coord(x,     y - 1);
		case E: return new Coord(x + 1, y);
		case S: return new Coord(x,     y + 1);
		case W: return new Coord(x - 1, y);
		default: return null;
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
			default: return null;
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
			default: return null;
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
		default: return null;
		}
	}

	// For 4 directions maps
	int distanceMan(Coord other) {
		int deltaX = abs(x - other.x);
		int deltaY = abs(y - other.y);
		return deltaX + deltaY;
	}

	// For 8 directions maps
	int distance(Coord other) {
		int deltaX = abs(x - other.x);
		int deltaY = abs(y - other.y);
		return max(deltaX, deltaY);
	}

	// For physics engine
	double distanceEcl(Coord other) {
		int deltaX = x - other.x;
		int deltaY = y - other.y;
		return sqrt((deltaX * deltaX) + (deltaY * deltaY));
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		Coord other = (Coord) obj;
		return (x == other.x) && (y == other.y);
	}

	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}
