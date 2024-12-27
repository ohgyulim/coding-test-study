package dfs_bfs.level3;

import java.util.*;

public class PGS_부대복귀 {
	Map<Integer, List<Integer>> graph = new HashMap<>();
	Integer[] distances;
	public int[] solution(int n, int[][] roads, int[] sources, int destination) {
		for (int i = 1; i <= n; i++) {
			graph.put(i, new ArrayList<>());
		}
		for (int[] road : roads) {
			graph.get(road[0]).add(road[1]);
			graph.get(road[1]).add(road[0]);
		}

		distances = new Integer[n + 1];
		bfs(destination);

		int index = 0;
		int[] answer = new int[sources.length];
		for (int source : sources) {
			answer[index++] = distances[source] == null ? -1 : distances[source];
		}

		return answer;
	}

	private void bfs(int dest) {
		Queue<Integer> queue = new LinkedList<>();

		queue.add(dest);
		distances[dest] = 0;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			List<Integer> nodes = graph.get(current); // current와 연결된 노드들
			for (int node : nodes) {
				if (distances[node] == null) {
					distances[node] = distances[current] + 1;
					queue.add(node);
				}
			}
		}
	}
}
