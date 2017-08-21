package training.hard.tanNetwork;

import static java.lang.Math.*;

import java.util.*;

class BusStop {
	final String id;
	final String name;
	final double lati;
	final double longi;
	
	Collection<BusLink> links = Collections.emptyList();
	double pathDist;
	BusStop pathPrev;

	BusStop(Scanner in) {
		String[] data = in.nextLine().split(",");
		id = data[0];
		String nameQuote = data[1];
		name = nameQuote.substring(1, nameQuote.length() - 1);
		lati = toRadians(Double.parseDouble(data[3]));
		longi = toRadians(Double.parseDouble(data[4]));
	}
	
	double distanceGeo(BusStop other) {
		double x = (longi - other.longi) * cos((lati + other.lati) / 2.0);
		double y = (lati - other.lati);
		return sqrt(x * x + y * y) * 6371.0;
	}
	
	void addLink(BusLink link) {
		if (links.isEmpty()) {
			links = new ArrayList<>();
		}
		links.add(link);
	}
}

class BusLink {
	final BusStop from;
	final BusStop to;

	BusLink(BusStop from, BusStop to) {
		this.from = from;
		this.to = to;
	}
	
	double distanceGeo() {
		return from.distanceGeo(to);
	}
}

class DistanceFrom implements Comparator<BusStop> {
	final BusStop target;
	
	DistanceFrom(BusStop target) {
		this.target = target;
	}

	public int compare(BusStop o1, BusStop o2) {
		return Double.compare(target.distanceGeo(o1), target.distanceGeo(o2));
	}
}

class Graph {
	static final Collection<BusStop> NO_PATH = new ArrayList<>();
	
	final Map<String, BusStop> stopsById;

	Graph(Scanner in) {
		int stopCnt = in.nextInt();
		if (in.hasNextLine()) {
			in.nextLine();
		}
		stopsById = new HashMap<>(stopCnt * 2);
		for (int stopIdx = 0; stopIdx < stopCnt; stopIdx++) {
			BusStop stop = new BusStop(in);
			stopsById.put(stop.id, stop);
		}
		int linkCnt = in.nextInt();
		for (int i = 0; i < linkCnt; i++) {
			BusStop from = stopsById.get(in.next());
			BusStop to = stopsById.get(in.next());
			BusLink link = new BusLink(from, to);
			from.addLink(link);
		}
	}

	BusStop getBusStop(String id) {
		return stopsById.get(id);
	}

	Collection<BusStop> shortestPath(BusStop begin, BusStop end) {
		for (BusStop node : stopsById.values()) {
			node.pathDist = Double.POSITIVE_INFINITY;
		}
		begin.pathDist = 0.0;

		//Comparator<BusStop> heuristic = new DistanceFrom(end);
		//Queue<BusStop> toUpdate = new PriorityQueue<>(heuristic);
		Queue<BusStop> toUpdate = new ArrayDeque<>();
		toUpdate.add(begin);
		while (!toUpdate.isEmpty()) {
			BusStop node = toUpdate.poll();
			for (BusLink link : node.links) {
				BusStop next = link.to;
				double newDist = node.pathDist + link.distanceGeo();
				if ((newDist < end.pathDist) && (next.pathDist > newDist)) {
					next.pathDist = newDist;
					next.pathPrev = node;
					toUpdate.add(next); // queue may contains duplicates
				}
			}
		}
		
		if (end.pathDist == Double.POSITIVE_INFINITY) {
			return NO_PATH;
		}
		
		Deque<BusStop> path = new ArrayDeque<>();
		BusStop node = end;
		path.addFirst(node);
		while (node != begin) {
			node = node.pathPrev;
			path.addFirst(node);
		}
		return path;
	}

}

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		String beginId = in.next();
		String endId = in.next();
		Graph graph = new Graph(in);

		BusStop begin = graph.getBusStop(beginId);
		BusStop end = graph.getBusStop(endId);
		Collection<BusStop> path = graph.shortestPath(begin, end);
		if (path == Graph.NO_PATH) {
			System.out.println("IMPOSSIBLE");
		} else {
			for (BusStop stop : path) {
				System.out.println(stop.name);
			}
		}
	}

}
