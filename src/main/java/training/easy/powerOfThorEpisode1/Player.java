package training.easy.powerOfThorEpisode1;

import java.util.*;

class Player {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int lightX = in.nextInt(); // the X position of the light of power
		int lightY = in.nextInt(); // the Y position of the light of power
		int thorX = in.nextInt(); // Thor's starting X position
		int thorY = in.nextInt(); // Thor's starting Y position

		while (true) {
			int remainingTurns = in.nextInt(); // The remaining amount of turns Thor can move.

			String direction = "";
			if (lightY > thorY) {
				direction = "S";
				thorY++;
			} else if (lightY < thorY) {
				direction = "N";
				thorY--;
			}

			if (lightX > thorX) {
				direction += 'E';
				thorX++;
			} else if (lightX < thorX) {
				direction += 'W';
				thorX--;
			}

			// A single line providing the move to be made: N NE E SE S SW W or NW
			System.out.println(direction);
		}
	}

}
