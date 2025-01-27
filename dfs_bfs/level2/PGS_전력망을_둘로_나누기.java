package dfs_bfs.level2;

import java.util.*;

public class PGS_전력망을_둘로_나누기 {
	Map<Integer, List<Integer>> graph = new HashMap<>();
	boolean[] isVisited;

	public int solution(int n, int[][] wires) {
		for (int i = 1; i <= n; i++) {
			graph.put(i, new ArrayList<>());
		}

		for (int[] wire : wires) {
			int start = wire[0];
			int end = wire[1];
			graph.get(start).add(end);
			graph.get(end).add(start);
		}

		int answer = Integer.MAX_VALUE;

		for (int[] wire : wires) {
			int start = wire[0];
			int end = wire[1];

			graph.get(start).remove((Integer)end);
			graph.get(end).remove((Integer)start);

			isVisited = new boolean[n + 1];
			int size = dfs(start);

			int diff = Math.abs((n - size) - size);
			answer = Math.min(answer, diff);

			graph.get(start).add(end);
			graph.get(end).add(start);
		}

		return answer;
	}

	private int dfs(int node) {
		isVisited[node] = true;
		int count = 1;

		for (int nextNode : graph.get(node)) {
			if (!isVisited[nextNode]) {
				count += dfs(nextNode);
			}
		}

		return count;
	}
}
