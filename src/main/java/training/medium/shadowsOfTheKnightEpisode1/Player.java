package training.medium.shadowsOfTheKnightEpisode1;

import java.util.*;

class Coord {
	int x;
	int y;

	Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	Coord(Scanner in) {
		x = in.nextInt();
		y = in.nextInt();
		//System.err.println(toString());
	}

	public String toString() {
		return x + " " + y;
	}
}

class Player {

	public static void main(String[] args) {
		new Player().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		Coord size = new Coord(in);
		int remains = in.nextInt(); // maximum number of turns before game over.
		Coord pos = new Coord(in);

		// Search window
		Coord min = new Coord(0, 0);
		Coord max = new Coord(size.x - 1, size.y - 1);

		while (true) {
			String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

			if (bombDir.contains("L")) {
				max.x = pos.x - 1;
			} else if (bombDir.contains("R")) {
				min.x = pos.x + 1;
			} else {
				min.x = pos.x; 
				max.x = pos.x;
			}

			if (bombDir.contains("D")) {
				min.y = pos.y + 1;
			} else if (bombDir.contains("U")) {
				max.y = pos.y - 1;
			} else {
				min.y = pos.y;
				max.y = pos.y;
			}

			pos.x = ((max.x - min.x) / 2) + min.x;
			pos.y = ((max.y - min.y) / 2) + min.y;

			System.out.println(pos); // the location of the next window Batman should jump to.
		}
	}

}
