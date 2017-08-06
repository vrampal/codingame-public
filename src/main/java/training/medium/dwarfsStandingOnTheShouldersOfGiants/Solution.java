package training.medium.dwarfsStandingOnTheShouldersOfGiants;

import static java.lang.Math.*;

import java.util.*;

class Node {
	final int id;
	final Collection<Node> adjNodes = new ArrayList<>();

	int rank;

	Node(int id) {
		this.id = id;
	}
}

class Graph {
	private final Map<Integer, Node> nodesById = new HashMap<>();
	
	Graph(Scanner in) {
		int edgeCnt = in.nextInt(); // the number of relationships of influence
		for (int edgeIdx = 0; edgeIdx < edgeCnt; edgeIdx++) {
			int fromId = in.nextInt(); // a relationship of influence between two people (x influences y)
			int toId = in.nextInt();
			createEdge(fromId, toId);
		}
	}

	private void createEdge(int fromId, int toId) {
		Node from = findNode(fromId);
		Node to = findNode(toId);
		from.adjNodes.add(to);
		//System.err.println("Edge " + fromId + " -> " + toId);
	}

	private Node findNode(int nodeId) {
		Node node = nodesById.get(nodeId);
		if (node == null) {
			node = new Node(nodeId);
			nodesById.put(nodeId, node);
		}
		return node;
	}

	Collection<Node> getNodes() {
		return nodesById.values();
	}

	int longestPathFrom(Node root) {
		for (Node node : getNodes()) {
			node.rank = -1;
		}
		Queue<Node> curQueue = new ArrayDeque<>();
		Queue<Node> nextQueue = new ArrayDeque<>();
		int curRank = 0;
		curQueue.add(root);
		//System.err.println("Root is " + root.id);
		while (!curQueue.isEmpty() || !nextQueue.isEmpty()) {
			while (!curQueue.isEmpty()) {
				Node node = curQueue.poll();
				node.rank = max(node.rank, curRank);
				//System.err.println("Node " + node.id + " is rank " + node.rank);
				nextQueue.addAll(node.adjNodes);
			}
			curRank++;
			curQueue = nextQueue;
			nextQueue = new ArrayDeque<>();
		}
		return curRank;
	}
}

class Solution {

	public static void main(String args[]) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		Graph graph = new Graph(in);

		int longestPath = 0;

		for (Node node : graph.getNodes()) {
			int path = graph.longestPathFrom(node);
			longestPath = max(path, longestPath);
		}

		// The number of people involved in the longest succession of influences
		System.out.println(longestPath);
	}

}
