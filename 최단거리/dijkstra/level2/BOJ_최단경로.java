package 최단거리.dijkstra.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_최단경로 {
	static class Node implements Comparable<Node> {
		int vertex;
		int weight;

		Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node other) {
			return this.weight - other.weight; // 거리 기준 오름차순 정렬
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}

		int startV = Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, w));
		}

		int[] distances = dijkstra(graph, V, startV);

		for (int i = 1; i <= V; i++) {
			if (distances[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(distances[i]);
			}
		}
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
