package training.community.asciiGraph;

import static java.lang.Math.*;

import java.util.*;

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

class Solution {

	public static void main(String args[]) {
		new Solution().run();
	}
	Scanner in = new Scanner(System.in);

	void run() {
		int minX = 0;
		int maxX = 0;
		int minY = 0;
		int maxY = 0;

		Collection<Coord> points = new HashSet<>();
		int pointCount = in.nextInt();
		for (int i = 0; i < pointCount; i++) {
			Coord point = new Coord(in);
			points.add(point);
			minX = min(minX, point.x);
			maxX = max(maxX, point.x);
			minY = min(minY, point.y);
			maxY = max(maxY, point.y);
		}

		minX--;
		maxX++;
		minY--;
		maxY++;

		for(int y = maxY; y >= minY; y--) {
			StringBuilder builder = new StringBuilder();
			for (int x = minX; x <= maxX; x++) {
				Coord pos = new Coord(x, y);
				if (points.contains(pos)) {
					builder.append('*');
				} else if ((x == 0) && (y == 0)) {
					builder.append('+');
				} else if (x == 0) {
					builder.append('|');
				} else if (y == 0) {
					builder.append('-');
				} else {
					builder.append('.');
				}
			}
			System.out.println(builder);
		}
	}

}
