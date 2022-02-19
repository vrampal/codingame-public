package training.easy.marsLanderEpisode1;

import java.util.*;

class Player {

	private static final int MIN_VERT_SPEED = -39;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int pointCount = in.nextInt(); // the number of points used to draw the surface of Mars.
		for (int i = 0; i < pointCount; i++) {
			int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
			int landY = in.nextInt(); // Y coordinate of a surface point.
			// By linking all the points together in a sequential fashion, you form the surface of Mars.
		}

		while (true) {
			int x = in.nextInt();
			int y = in.nextInt();
			int vx = in.nextInt(); // the horizontal speed (in m/s), can be negative.
			int vy = in.nextInt(); // the vertical speed (in m/s), can be negative.
			int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
			int rot = in.nextInt(); // the rotation angle in degrees (-90 to 90).
			int power = in.nextInt(); // the thrust power (0 to 4).

			int angle = 0;
			int thrustPower = 0;
			if (vy < MIN_VERT_SPEED) {
				thrustPower = 4;
			}

			// R P. R is the desired rotation angle. P is the desired thrust power.
			System.out.println(angle + " " + thrustPower);
		}
	}

}
