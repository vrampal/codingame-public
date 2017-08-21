package training.community.gameOfLife;

import java.util.*;

class Board {
	final int width;
	final int height;
	final String[] rows;
	
	private Board(int width, int height) {
		this.width = width;
		this.height = height;
		rows = new String[height];
	}

	Board(Scanner in) {
		this(in.nextInt(), in.nextInt());
		if (in.hasNextLine()) {
			in.nextLine();
		}
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			rows[rowIdx] = in.next();
		}
	}
	
	boolean cellExist(int x, int y) {
		return ((y >= 0) && (y < height) && (x >= 0) && (x < width));
	}

	private char getCell(int x, int y) {
		return rows[y].charAt(x);
	}

	private int alive(int x, int y) {
		int alive = 0;
		if (cellExist(x, y)) {
			alive = getCell(x, y) - '0';
		}
		return alive;
	}
	
	char nextAlive(int x, int y) {
		int neighbour = 0;
		neighbour += alive(x    , y - 1);
		neighbour += alive(x + 1, y - 1);
		neighbour += alive(x + 1, y);
		neighbour += alive(x + 1, y + 1);
		neighbour += alive(x    , y + 1);
		neighbour += alive(x - 1, y + 1);
		neighbour += alive(x - 1, y);
		neighbour += alive(x - 1, y - 1);
		char nextAlive = getCell(x, y);
		if ((nextAlive == '1') && (neighbour < 2) || (neighbour > 3)) {
			nextAlive = '0';
		} else if ((nextAlive == '0') && (neighbour == 3)) {
			nextAlive = '1';
		}
		return nextAlive;
	}
}

class Solution {

    public static void main(String[] args) {
    	new Solution().run();
    }

    Scanner in = new Scanner(System.in);
    
    void run() {
    	Board board = new Board(in);
    	StringBuilder builder = new StringBuilder();
    	for (int y = 0; y < board.height; y++) {
    		for (int x = 0; x < board.width; x++) {
    			char ch = board.nextAlive(x, y);
    			builder.append(ch);
    		}
    		builder.append('\n');
    	}
    	System.out.println(builder);
    }

}
