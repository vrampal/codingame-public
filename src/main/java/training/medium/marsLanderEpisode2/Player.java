package training.medium.marsLanderEpisode2;

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
		int x = (from.x + to.x) / 2;
		int y = (from.y + to.y) / 2;
		return new Coord(x, y);
	}
}

class Mobile {
	Coord pos;
	Coord speed;

	Mobile(Scanner in) {
		pos = new Coord(in);
		speed = new Coord(in);
	}
}

class Lander {
	int angle;
	int power;

	Lander(int angle, int power) {
		this.angle = angle;
		this.power = power;
	}

	Lander(Scanner in) {
		angle = in.nextInt();
		power = in.nextInt();
	}

	public String toString() {
		return angle + " " + power;
	}
}

class Player {

	private static final int MIN_VERT_SPEED = -35;
	private static final int MAX_ROT = 18;
	private static final int FINAL_HEIGHT = 100;

	public static void main(String args[]) {
		new Player().run();
	}

	Scanner in = new Scanner(System.in);
	Mobile mobile;

	void run() {
		int pointCount = in.nextInt();
		Segment safeZone = null;
		Coord[] ground = new Coord[pointCount];
		Coord prevPoint = new Coord(in);
		ground[0] = prevPoint;
		for (int pointIdx = 1; pointIdx < pointCount; pointIdx++) {
			Coord point = new Coord(in);
			ground[pointIdx] = point;
			if (prevPoint.y == point.y) {
				safeZone = new Segment(prevPoint, point);
			}
			prevPoint = point;
		}

		Coord target = safeZone.middle();
		System.err.println("target: " + target);

		while (true) {
			Mobile mobile = new Mobile(in);
			int fuel = in.nextInt();
			Lander lander = new Lander(in);

			Lander order = new Lander(0, 3);
			int xMin = safeZone.from.x;
			int xMax = safeZone.to.x;
			if (mobile.pos.x < xMin) {
				order.angle = -MAX_ROT;
			} else if (mobile.pos.x > xMax) {
				order.angle = MAX_ROT;
			} else if (mobile.speed.x > 0) {
				order.angle = MAX_ROT;
			} else if (mobile.pos.x < 0) {
				order.angle = -MAX_ROT;
			}

			if (mobile.pos.y < target.y + FINAL_HEIGHT) {
				order.angle = 0;
			}

			if (mobile.speed.y < MIN_VERT_SPEED) {
				order.power = 4;
			}

			System.out.println(order);
		}
	}

}
