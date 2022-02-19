package training.medium.dontPanicEpisode1;

import java.util.*;

enum Direction {
	NONE, LEFT, RIGHT;
}

enum Action {
	WAIT, BLOCK;
}

class Player {

	public static void main(String[] args) {
		new Player().run();
	}
	
	Scanner in = new Scanner(System.in);
	
	void run() {
		int floorCount = in.nextInt(); // number of floors
		int width = in.nextInt(); // width of the area

		int[] targetPerFloor = new int[floorCount];

		int nbRounds = in.nextInt(); // maximum number of rounds
		int exitFloor = in.nextInt(); // floor on which the exit is found
		int exitPos = in.nextInt(); // position of the exit on its floor

		targetPerFloor[exitFloor] = exitPos;

		int nbTotalClones = in.nextInt(); // number of generated clones
		int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
		int nbElevators = in.nextInt(); // number of elevators
		for (int elevatorIdx = 0; elevatorIdx < nbElevators; elevatorIdx++) {
			int elevatorFloor = in.nextInt(); // floor on which this elevator is found
			int elevatorPos = in.nextInt(); // position of the elevator on its floor

			targetPerFloor[elevatorFloor] = elevatorPos;
		}

		while (true) {
			int cloneFloor = in.nextInt(); // floor of the leading clone
			int clonePos = in.nextInt(); // position of the leading clone on its floor
			Direction direction = Direction.valueOf(in.next()); // direction of the leading clone: LEFT or RIGHT

			Action action = Action.WAIT;
			// Only if we have an active clone
			if (cloneFloor != -1) {
				int targetPos = targetPerFloor[cloneFloor];

				if (((targetPos < clonePos) && (direction == Direction.RIGHT))
						|| ((targetPos > clonePos) && (direction == Direction.LEFT))) {
					action = Action.BLOCK;
				}
			}

			System.out.println(action); // action: WAIT or BLOCK
		}
	}

}
