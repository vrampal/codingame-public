package training.hard.benderEpisode2;

import java.util.*;

class Room {
	static final Room EXIT = new Room(-1);
	
	final int id;
	int amount = 0;
	Room doorA = null;
	Room doorB = null;
	
	int maxMoney = 0;
	
	Room(int id) {
		this.id = id;
	}
}

class Solution {

    public static void main(String args[]) {
    	new Solution().run();
    }
	
    Scanner in = new Scanner(System.in);
    Map<Integer, Room> roomsById;
    
    void run() {
        int roomCnt = in.nextInt();
    	roomsById = new HashMap<>(roomCnt * 2);
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int roomIdx = 0; roomIdx < roomCnt; roomIdx++) {
        	String roomStr = in.nextLine();
        	//System.err.println(roomStr);
        	String[] roomData = roomStr.split(" ");
        	Room room = findRoom(roomData[0]);
        	room.amount = Integer.parseInt(roomData[1]);
        	room.doorA = findRoom(roomData[2]);
        	room.doorB = findRoom(roomData[3]);
        }
        
        Queue<Room> queue = new ArrayDeque<>();
        Room start = findRoom(0);
        start.maxMoney = start.amount;
        queue.add(start);
        while (!queue.isEmpty()) {
        	Room room = queue.poll();
        	Room nextA = room.doorA;
			if ((nextA != null) && (nextA.maxMoney < (room.maxMoney + nextA.amount))) {
        		nextA.maxMoney = (room.maxMoney + nextA.amount);
        		queue.add(nextA);
        	}
        	Room nextB = room.doorB;
			if ((nextB != null) && (nextB.maxMoney < (room.maxMoney + nextB.amount))) {
        		nextB.maxMoney = (room.maxMoney + nextB.amount);
        		queue.add(nextB);
        	}
        }

        System.out.println(Room.EXIT.maxMoney);
    }
    
    Room findRoom(String roomId) {
    	if ("E".equals(roomId)) {
    		return Room.EXIT;
    	}
    	return findRoom(Integer.parseInt(roomId));
    }
    
    Room findRoom(int roomId) {
    	Room room = roomsById.get(roomId);
    	if (room == null) {
    		room = new Room(roomId);
    		roomsById.put(roomId, room);
    	}
    	return room;
    }

}
