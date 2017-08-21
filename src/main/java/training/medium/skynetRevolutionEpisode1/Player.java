package training.medium.skynetRevolutionEpisode1;

import java.util.*;

class Node {
	final int id;
	final Collection<Node> adjNodes = new ArrayList<>();

	boolean isExit = false;
	int enemyDistance;
	int adjExitCount;

	Node(int id) {
		this.id = id;
	}

	void recuseEnemyDistance(int newEnemyDistance) {
		enemyDistance = newEnemyDistance;
		if (!isExit) {
			for (Node node : adjNodes) {
				if (node.enemyDistance > enemyDistance + 1) {
					node.recuseEnemyDistance(enemyDistance + 1); 
				}
			}
		}
	}

	Node firstAdjExit() {
		for (Node node : adjNodes) {
			if (node.isExit) {
				return node;
			}
		}
		throw new RuntimeException("Can't find adj exit on node " + id);
	}
}

class Graph {
	final Node[] nodes;

	Graph(int nodeCount) {
		nodes = new Node[nodeCount];
		for (int nodeIdx = 0; nodeIdx < nodeCount; nodeIdx++) {
			Node node = new Node(nodeIdx);
			nodes[nodeIdx] = node;
		}
	}

	Node getNode(int nodeIdx) {
		return nodes[nodeIdx];
	}

	void createEdge(int node1idx, int node2idx) {
		Node node1 = getNode(node1idx);
		Node node2 = getNode(node2idx);
		node1.adjNodes.add(node2);
		node2.adjNodes.add(node1);
	}

	void destroyEdge(Node node1, Node node2) {
		node1.adjNodes.remove(node2);
		node2.adjNodes.remove(node1);
	}

	void recompute(int enemyNodeIdx) {
		// Reset nodes
		for (Node node : nodes) {
			node.enemyDistance = Integer.MAX_VALUE;
			node.adjExitCount = 0;
		}
		// Update adjExitCount
		for (Node node : nodes) {
			if (node.isExit) {
				for (Node adj : node.adjNodes) {
					adj.adjExitCount++;
				}
			}
		}
		// Update enemyDist
		Node enemyNode = getNode(enemyNodeIdx);
		enemyNode.recuseEnemyDistance(0);
	}
}

class Player {

	public static void main(String[] args) {
		new Player().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		int nodeCount = in.nextInt(); // the total number of nodes in the level, including the gateways
		int edgeCount = in.nextInt(); // the number of links
		int exitCount = in.nextInt(); // the number of exit gateways
		Graph graph = new Graph(nodeCount);

		for (int edgeIdx = 0; edgeIdx < edgeCount; edgeIdx++) {
			int node1idx = in.nextInt(); // N1 and N2 defines a link between these nodes
			int node2idx = in.nextInt();
			graph.createEdge(node1idx, node2idx);
		}

		for (int i = 0; i < exitCount; i++) {
			int exitNodeIdx = in.nextInt(); // the index of a gateway node
			Node exitNode = graph.getNode(exitNodeIdx);
			exitNode.isExit = true;
		}

		while (true) {
			int enemyNodeIdx = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
			graph.recompute(enemyNodeIdx);

			Node node1 = null;
			int node1dist = Integer.MAX_VALUE;
			for (Node node : graph.nodes) {
				if ((node.adjExitCount > 0) && (node1dist > node.enemyDistance)) {
					node1dist = node.enemyDistance;
					node1 = node;
				}
			}
			Node node2 = node1.firstAdjExit();
			graph.destroyEdge(node1, node2);

			// Example: 0 1 are the indices of the nodes you wish to sever the link between
			System.out.println(node1.id + " " + node2.id);
		}
	}

}
