package 최단거리.dijkstra.level3;

import java.util.*;

public class PGS_등산코스_정하기 {
	class Node {
		int number;
		int weight;

		Node(int number, int weight) {
			this.number = number;
			this.weight = weight;
		}
	}

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

		Map<Integer, List<Node>> graph = new HashMap<>();
		for (int[] path : paths) {
			int start = path[0];
			int end = path[1];
			int weight = path[2];
			if (!graph.containsKey(start)) graph.put(start, new ArrayList<>());
			if (!graph.containsKey(end)) graph.put(end, new ArrayList<>());
			graph.get(start).add(new Node(end, weight));
			graph.get(end).add(new Node(start, weight));
		}

		boolean[] isGate = new boolean[n + 1];
		boolean[] isSummit = new boolean[n + 1];
		for (int gate : gates) isGate[gate] = true;
		for (int summit : summits) isSummit[summit] = true;

		int[] minIntensity = new int[n + 1];
		Arrays.fill(minIntensity, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);

		for (int gate : gates) {
			pq.add(new Node(gate, 0));
			minIntensity[gate] = 0;
		}

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			int currentNode = current.number;
			int currentWeight = current.weight;

			if (currentWeight > minIntensity[currentNode]) continue;

			for (Node node : graph.getOrDefault(currentNode, new ArrayList<>())) {
				int nextNode = node.number;
				int nextWeight = Math.max(currentWeight, node.weight);

				if (isGate[nextNode]) continue;

				if (nextWeight < minIntensity[nextNode]) {
					minIntensity[nextNode] = nextWeight;
					if (!isSummit[nextNode]) pq.add(new Node(nextNode, nextWeight));
				}
			}
		}

		int bestSummit = -1;
		int bestIntensity = Integer.MAX_VALUE;

		Arrays.sort(summits);
		for (int summit : summits) {
			if (minIntensity[summit] < bestIntensity) {
				bestIntensity = minIntensity[summit];
				bestSummit = summit;
			}
		}

		return new int[]{bestSummit, bestIntensity};
	}

}
