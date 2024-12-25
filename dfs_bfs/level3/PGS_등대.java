package dfs_bfs.level3;

import java.util.*;

public class PGS_등대 {
	boolean[] isVisited = new boolean[100_001];
	Map<Integer, List<Integer>> graph = new HashMap<>();
	public int solution(int n, int[][] lighthouse) {
		int answer = 0;
		for (int[] info : lighthouse) {
			int edge1 = info[0];
			int edge2  = info[1];
			if (!graph.containsKey(edge1)) graph.put(edge1, new ArrayList<>());
			if (!graph.containsKey(edge2)) graph.put(edge2, new ArrayList<>());
			graph.get(edge1).add(edge2);
			graph.get(edge2).add(edge1);
		}

		recur(1, 1);
		for (int i = 1; i <= n; i++) {
			if (isVisited[i]) answer += 1;
		}
		return answer;
	}

	public void recur(int parent, int current) {
		List<Integer> nodes = graph.get(current);
		for (int child : nodes) {
			if (parent == child) continue; // 무한 재귀 방지
			recur(current, child);
			if (!isVisited[child]) isVisited[current] = true;
		}
	}
}
