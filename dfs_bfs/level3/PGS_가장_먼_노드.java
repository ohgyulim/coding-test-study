package dfs_bfs.level3;

import java.util.*;

public class PGS_가장_먼_노드 {
	int INF = Integer.MAX_VALUE;
	public int solution(int n, int[][] edges) {
		int answer = 0;
		List<Integer>[] graph = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int[] edge : edges) {
			int from = edge[0];
			int to = edge[1];
			graph[from].add(to);
			graph[to].add(from);
		}

		int maxDist = -1;
		int[] dist = new int[n + 1];
		Arrays.fill(dist, INF);
		dist[1] = 0;

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);

		while (!queue.isEmpty()) {
			int node = queue.poll();
			List<Integer> children = graph[node];

			for (int child : children) {
				if (dist[child] > dist[node] + 1) {
					dist[child] = dist[node] + 1;
					maxDist = Math.max(maxDist, dist[child]);
					queue.offer(child);
				}
			}
		}

		for (int node = 1; node <= n; node++) {
			if (dist[node] == maxDist) answer += 1;
		}

		return answer;
	}
}
