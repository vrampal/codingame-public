package training.medium.teadsSponsoredContest;

import java.util.*;

class Node {
	final int id;
	final Collection<Node> adj = new ArrayList<>();

	Node(int id) {
		this.id = id;
	}
}

class Graph {
	final Map<Integer, Node> nodesById = new HashMap<>();

	Node findNode(int nodeId) {
		Node node = nodesById.get(nodeId);
		if (node == null) {
			node = new Node(nodeId);
			nodesById.put(nodeId, node);
		}
		return node;
	}

	void createEdge(int id1, int id2) {
		Node node1 = findNode(id1);
		Node node2 = findNode(id2);
		node1.adj.add(node2);
		node2.adj.add(node1);
	}

	int reduce() {
		int round = 0;
		Queue<Node> toDelete = new ArrayDeque<>();

		//System.err.println("Round " + round + " size " + nodesById.size());
		for (Node node : nodesById.values()) {
			if (node.adj.size() <= 1) {
				toDelete.add(node);
			}
		}

		while (!toDelete.isEmpty()) {
			for (Node node : toDelete) {
				removeNode(node);
			}
			round++;
			
			if (nodesById.size() <= 1) {
				return round;
			}

			toDelete = new ArrayDeque<>();
			//System.err.println("Round " + round + " size " + nodesById.size());
			for (Node node : nodesById.values()) {
				if (node.adj.size() <= 1) {
					toDelete.add(node);
				}
			}
		}
		return round;
	}

	void removeNode(Node node) {
		System.err.println("Removing node " + node.id);
		nodesById.remove(node.id);
		for (Node other : node.adj) {
			other.adj.remove(node);
		}
	}
}

class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		Graph graph = new Graph();

		int edgeCnt = in.nextInt(); // the number of adjacency relations
		for (int edgeIdx = 0; edgeIdx < edgeCnt; edgeIdx++) {
			int xi = in.nextInt(); // the ID of a person which is adjacent to yi
			int yi = in.nextInt(); // the ID of a person which is adjacent to xi
			graph.createEdge(xi, yi);
			System.err.println("Edge " + xi + " - " + yi);
		}
		
		int reduce = graph.reduce();
		// TODO analyze if there are cycles

		// The minimal amount of steps required to completely propagate the advertisement
		System.out.println(reduce);
	}

}
