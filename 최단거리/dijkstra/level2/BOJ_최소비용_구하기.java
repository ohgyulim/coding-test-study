package 최단거리.dijkstra.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_최소비용_구하기 {
	static class Node implements Comparable<Node> {
		int vertex;
		int weight;

		Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node other) {
			return this.weight - other.weight;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());

		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, w));
		}
		st = new StringTokenizer(br.readLine(), " ");
		int startV = Integer.parseInt(st.nextToken());
		int endV = Integer.parseInt(st.nextToken());

		int[] distances = dijkstra(graph, V, startV);

		System.out.println(distances[endV]);
	}
	static int[] dijkstra(List<List<Node>> graph, int V, int start) {
		int[] distances = new int[V + 1];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			int currentVertex = current.vertex;
			int currentWeight = current.weight;

			if (currentWeight > distances[currentVertex]) {
				continue;
			}

			for (Node node : graph.get(currentVertex)) {
				int nextVertex = node.vertex;
				int weight = node.weight;

				if (distances[currentVertex] + weight < distances[nextVertex]) {
					distances[nextVertex] = distances[currentVertex] + weight;
					pq.offer(new Node(nextVertex, distances[nextVertex]));
				}
			}
		}

		return distances;
	}
}
