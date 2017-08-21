package training.medium.benderEpisode1;

import java.util.*;

enum Direction {
	SOUTH, EAST, NORTH, WEST;
}

class Coord {
	final int x;
	final int y;

	Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	Coord add(Direction dir) {
		switch (dir) {
		case NORTH: return new Coord(x,     y - 1);
		case EAST:  return new Coord(x + 1, y);
		case SOUTH: return new Coord(x,     y + 1);
		case WEST:  return new Coord(x - 1, y);
		default:    throw new IllegalArgumentException("Invalid dir: " + dir);
		}
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
		if (getClass() != obj.getClass()) return false;
		Coord other = (Coord) obj;
		return (x == other.x) && (y == other.y);
	}

	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}

class Board {
	final int height;
	final int width;

	private final StringBuilder[] cells;

	Board(int height, int width) {
		this.height = height;
		this.width = width;
		//System.err.println(height + " " + width);
		cells = new StringBuilder[height];
	}
	
	Board(Scanner in) {
		this(in.nextInt(), in.nextInt());
		if (in.hasNextLine()) {
			in.nextLine();
		}
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			String row = in.nextLine();
			//System.err.println(row);
			cells[rowIdx] = new StringBuilder(row);
		}
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
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				char val = getCellAt(row, col);
				if (val == target) {
					return new Coord(col, row);
				}
			}
		}
		throw new RuntimeException("Can't find first " + target);
	}

	Coord findSecond(char target, Coord first) {
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				char val = getCellAt(row, col);
				if ((val == target) && ((col != first.x) || (row != first.y))) {
					return new Coord(col, row);
				}
			}
		}
		throw new RuntimeException("Can't find second " + target);
	}
}

class Bender {
	Coord pos;
	Direction dir = Direction.SOUTH;
	boolean inverted = false;
	boolean breakMode = false;
	int breakCount;

	Bender() {
		// Allow default constructor
	}

	Bender(Bender other) {
		pos = other.pos;
		dir = other.dir;
		inverted = other.inverted;
		breakMode = other.breakMode;
		breakCount = other.breakCount;
	}

	Coord nextPos() {
		return pos.add(dir);
	}

	boolean canPass(char val) {
		switch (val) {
		case '#': return false;
		case 'X': return breakMode;
		default:  return true;
		}
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + breakCount;
		result = prime * result + (breakMode ? 1231 : 1237);
		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
		result = prime * result + (inverted ? 1231 : 1237);
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Bender other = (Bender) obj;
		if ((breakCount != other.breakCount) ||(breakMode != other.breakMode) || (dir != other.dir)
				|| (inverted != other.inverted))
			return false;
		return ((pos == other.pos) || (pos.equals(other.pos)));
	}

	public String toString() {
		return "Bender: " + pos + " " + dir + " " + inverted + " " + breakMode + " " + breakCount;
	}
}

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);
	private Board board;
	private Bender bender = new Bender();
	private final List<Bender> previousBender = new ArrayList<>();

	void run() {
		board = new Board(in);

		Coord start = board.findFirst('@');
		Coord end = board.findFirst('$');

		bender.pos = start;
		while (!bender.pos.equals(end)) {
			// Ensure we can move
			reorient();
			
			// Save actual state
			//System.err.println(bender);
			previousBender.add(new Bender(bender));

			// Do the move
			Coord nextPos = bender.nextPos();
			
			char nextCell = board.getCellAt(nextPos);
			if ((nextCell == 'X') && bender.breakMode) {
				bender.breakCount++;
				board.setCellAt(nextPos, ' ');
				nextCell = ' ';
			}
			bender.pos = nextPos;
			applyEffect(nextCell);

			if (previousBender.contains(bender)) {
				System.out.println("LOOP");
				return;
			}
		}
		
		for (Bender prev : previousBender) {
			System.out.println(prev.dir);
		}
	}

	void reorient() {
		boolean canPass = canPassNext();
		if (!canPass) {
			Direction[] directions = Direction.values();
			for (int dirIdx = 0; dirIdx < directions.length && !canPass; dirIdx++) {
				Direction potentialDir;
				if (bender.inverted) {
					potentialDir = directions[directions.length - dirIdx - 1];
				} else {
					potentialDir = directions[dirIdx];
				}
				bender.dir = potentialDir;
				canPass = canPassNext();
			}
		}
	}
	
	boolean canPassNext() {
		Coord targetPos = bender.nextPos();
		char targetCell = board.getCellAt(targetPos);
		return bender.canPass(targetCell);
	}

	void applyEffect(char cell) {
		switch (cell) {
		case 'S':
			bender.dir = Direction.SOUTH;
			break;
		case 'E':
			bender.dir = Direction.EAST;
			break;
		case 'N':
			bender.dir = Direction.NORTH;
			break;
		case 'W':
			bender.dir = Direction.WEST;
			break;
		case 'I':
			bender.inverted = !bender.inverted;
			break;
		case 'B':
			bender.breakMode = !bender.breakMode;
			break;
		case 'T':
			bender.pos = board.findSecond('T', bender.pos);
			break;
		}
	}

}
