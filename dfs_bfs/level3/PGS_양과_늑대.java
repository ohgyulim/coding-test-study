package dfs_bfs.level3;

import java.util.*;

// 모르겟다..
public class PGS_양과_늑대 {
	Map<Integer, List<Integer>> graph = new HashMap<>();
	int answer = 0;

	public int solution(int[] info, int[][] edges) {
		for (int[] edge : edges) {
			int parent = edge[0];
			int child = edge[1];
			graph.putIfAbsent(parent, new ArrayList<>());
			graph.get(parent).add(child);
		}

		Set<Integer> nextNodes = new HashSet<>();
		nextNodes.add(0);
		dfs(info, 0, 0, 0, nextNodes);

		return answer;
	}

	public void dfs(int[] info, int cur, int sheep, int wolf, Set<Integer> nextNodes) {
		sheep += info[cur] ^ 1;
		wolf += info[cur];

		if (wolf >= sheep) return;

		answer = Math.max(answer, sheep);

		Set<Integer> newNextNodes = new HashSet<>(nextNodes);
		newNextNodes.remove(cur);
		if (graph.containsKey(cur)) {
			newNextNodes.addAll(graph.get(cur));
		}

		for (int next : newNextNodes) {
			dfs(info, next, sheep, wolf, newNextNodes);
		}
	}
}
