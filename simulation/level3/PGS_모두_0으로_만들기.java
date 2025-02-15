package simulation.level3;

import java.util.*;

public class PGS_모두_0으로_만들기 {
	class Edge {
		int number;
		int weight;
		Edge(int number, int weight) {
			this.number = number;
			this.weight = weight;
		}
	}

	Map<Integer, List<Edge>> graph = new HashMap<>();
	PriorityQueue<Edge> edges = new PriorityQueue<>((o1, o2) -> Math.abs(o2.weight) - Math.abs(o1.weight));
	public long solution(int[] a, int[][] edges) {
		long answer = -1;
		for (int i = 0; i < a.length; i++) {
			graph.put(i, new ArrayList<>());
			edges.offer(new Edge(i, a[i]));
		}

		for (int[] edge : edges) {
			int from = edge[0];
			int to = edge[1];
			graph.get(from).add(new Edge(to, a[to]));
			graph.get(to).add(new Edge(from, a[from]));
		}

		return calc();
	}

	public int calc() {
		int count = 0;
		while (!edges.isEmpty()) {
			Edge edge = edges.poll();
			int number = edge.number;
			int weight = edge.weight;
			if (weight == 0) continue;
			for (Edge child : graph.get(number)) {
				if (!edges.remove(child)) continue;
				if (weight < 0 && child.weight < 0) continue;
				if (weight > 0 && child.weight > 0) continue;
				while (weight > 0) {
					weight -= 1;
					child.weight += 1;
					count += 1;
				}
				while (weight < 0) {
					weight += 1;
					child.weight -= 1;
					count += 1;
				}
				edges.offer(child);
			}
		}
		return count;
	}

	public void print() {
		for (var entrySet : graph.entrySet()) {
			System.out.println("노드: " + entrySet.getKey());
			for (Edge edge : entrySet.getValue()) {
				System.out.println(edge.number + " " + edge.weight);
			}
		}
	}
}
