package binary_search.level3;

import java.io.*;
import java.util.*;

public class BOJ_중량제한 {
	static class Edge {
		int to;
		int weight;
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	static int N;
	static Map<Integer, List<Edge>> graph = new HashMap<>();
	static int from, to;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= N; i++) {
			graph.put(i, new ArrayList<>());
		}

		int maxWeight = 0;
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Edge(to, weight));
			graph.get(to).add(new Edge(from, weight));
			maxWeight = Math.max(maxWeight, weight);
		}

		st = new StringTokenizer(br.readLine(), " ");
		from = Integer.parseInt(st.nextToken());
		to = Integer.parseInt(st.nextToken());

		System.out.println(binarySearch(maxWeight));


	}

	public static int binarySearch(int weight) {
		int left = 0;
		int right = weight;
		int maxWeight = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (canAnswer(mid)) {
				maxWeight = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return maxWeight;
	}

	public static boolean canAnswer(int weight) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		queue.offer(from);
		visited[from] = true;

		while (!queue.isEmpty()) {
			int edge = queue.poll();
			if (edge == to) return true;

			for (Edge target : graph.getOrDefault(edge, new ArrayList<>())) {
				if (!visited[target.to] && target.weight >= weight) {
					visited[target.to] = true;
					queue.offer(target.to);
				}
			}
		}
		return false;
	}
}
