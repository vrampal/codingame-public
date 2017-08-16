package training.hard.theBridgeEpisode2;

import static java.lang.Math.*;

import java.util.*;

enum Action {
	SPEED, WAIT, UP, DOWN, SLOW, JUMP;
}

class Bridge {
	static final int HEIGHT = 4;
	static final char ROAD = '.';

	final int width;
	final String[] lanes;

	Bridge(Scanner in) {
		lanes = new String[HEIGHT];
		for (int i = 0; i < HEIGHT; i++) {
			lanes[i] = in.next();
		}
		width = lanes[0].length();
	}

	private char getCellAt(int x, int y) {
		return lanes[y].charAt(x);
	}

	boolean isRoad(int x, int y) {
		return ((x >= width) || (getCellAt(x, y) == ROAD));
	}
}

class Bike {
	final int x;
	final int y;
	final boolean isAlive;

	private Bike(int x, int y, boolean alive) {
		this.x = x;
		this.y = y;
		this.isAlive = alive;
	}

	Bike(Scanner in) {
		this(in.nextInt(), in.nextInt(), (in.nextInt() == 1));
		//System.err.println(toString());
	}

	Bike applyMove(Bridge bridge, int speed) {
		int newX = x + speed;
		boolean newAlive = isAlive;
		for (int i = x + 1; i <= newX; i++) {
			newAlive &= bridge.isRoad(i, y);
		}
		return new Bike(newX, y, newAlive);
	}

	Bike applyMove(Bridge bridge, int speed, int deltaY) {
		int newX = x + speed;
		int newY = y + deltaY;
		boolean newAlive = isAlive;
		for (int i = x + 1; i < newX; i++) {
			newAlive &= bridge.isRoad(i, y);
			newAlive &= bridge.isRoad(i, newY);
		}
		newAlive &= bridge.isRoad(newX, newY);
		return new Bike(newX, newY, newAlive);
	}

	Bike applyJump(Bridge bridge, int speed) {
		int newX = x + speed;
		boolean newAlive = isAlive;
		newAlive &= bridge.isRoad(newX, y);
		return new Bike(newX, y, newAlive);
	}

	public String toString() {
		return x + " " + y + " " + isAlive;
	}
}

class State {
	final int minAlive;
	final Bridge bridge;
	final Bike[] bikes;
	final List<Action> actions;

	int speed;

	int countAlive;
	int x;
	int minY;
	int maxY;

	private State(int minAlive, Bridge road, Bike[] bikes, List<Action> actions, int speed) {
		this.minAlive = minAlive;
		this.bridge = road;
		this.bikes = bikes;
		this.actions = actions;
		this.speed = speed;
		analyzeAlive();
	}

	State(Scanner in) {
		int bikeCnt = in.nextInt(); // the amount of motorbikes to control
		minAlive = in.nextInt(); // the minimum amount of motorbikes that must survive
		bridge = new Bridge(in);
		bikes = new Bike[bikeCnt];
		actions = Collections.emptyList();
	}

	void updateFrom(Scanner in) {
		speed = in.nextInt(); // the motorbikes' speed
		for (int i = 0; i < bikes.length; i++) {
			bikes[i] = new Bike(in);
		}
		analyzeAlive();
	}
	
	private void analyzeAlive() {
		countAlive = 0;
		minY = Integer.MAX_VALUE;
		maxY = Integer.MIN_VALUE;
		for (Bike bike : bikes) {
			if (bike.isAlive) {
				countAlive++;
				x = bike.x;
				minY = min(minY, bike.y);
				maxY = max(maxY, bike.y);
			}
		}
		
	}

	boolean isValid() {
		return (countAlive >= minAlive);
	}

	boolean isVictory() {
		return (x > bridge.width);
	}

	boolean canApply(Action action) {
		switch (action) {
		case JUMP:
		case WAIT: return (speed > 0);
		case SLOW: return (speed > 2); // Reduce exploration and looks better :D
		case UP:   return (minY > 0);
		case DOWN: return (maxY < 3);
		default: return true;
		}		
	}

	State apply(Action action) {
		List<Action> newActions = new ArrayList<>(actions.size() + 1);
		newActions.addAll(actions);
		newActions.add(action);

		int newSpeed = speed;
		if (action == Action.SPEED) {
			newSpeed++;
		} else if (action == Action.SLOW) {
			newSpeed--;
		}

		Bike[] newBikes = new Bike[bikes.length];
		for (int i = 0; i < bikes.length; i++) {
			Bike bike = bikes[i];
			if (bike.isAlive) {
				switch (action) {
				case SPEED:
				case SLOW:
				case WAIT:
					bike = bike.applyMove(bridge, newSpeed);
					break;
				case UP:
					bike = bike.applyMove(bridge, newSpeed, -1);
					break;
				case DOWN:
					bike = bike.applyMove(bridge, newSpeed, +1);
					break;
				case JUMP:
					bike = bike.applyJump(bridge, newSpeed);
					break;
				}
			}
			newBikes[i] = bike;
		}

		return new State(minAlive, bridge, newBikes, newActions, newSpeed);
	}
}

class LongestRun implements Comparator<State> {
	static final LongestRun INSTANCE = new LongestRun();

	@Override
	public int compare(State o1, State o2) {
		int diff;
		diff = Integer.compare(o2.countAlive, o1.countAlive);
		if (diff == 0) {
			diff = Integer.compare(o2.x, o1.x);
		}
		return diff;
	}
}

class Player {

	public static void main(String args[]) {
		new Player().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		State actual = new State(in);

		// game loop
		while (true) {
			actual.updateFrom(in);
			Queue<State> queue = new PriorityQueue<>(LongestRun.INSTANCE);
			queue.add(actual);

			State target = queue.poll();
			while (!target.isVictory()) {
				for (Action action : Action.values()) {
					if (target.canApply(action)) {
						State future  = target.apply(action);
						if (future.isValid()) {
							queue.add(future);
						}
					}
				}
				target = queue.poll();
			}

			Action action = target.actions.get(0);

			// A single line containing one of 6 keywords: SPEED, SLOW, JUMP, WAIT, UP, DOWN.
			System.out.println(action);
		}
	}

}
