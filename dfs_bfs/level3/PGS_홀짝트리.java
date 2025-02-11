package dfs_bfs.level3;

import java.util.*;

public class PGS_홀짝트리 {
	Map<Integer, List<Integer>> graph = new HashMap<>();
	boolean[] visited;
	boolean[] checked; // 홀짝 = true / 역홀짝 = false;
	public int[] solution(int[] nodes, int[][] edges) {
		int[] answer = new int[2];
		int maxNode = Integer.MIN_VALUE;
		for (int node : nodes) {
			graph.put(node, new ArrayList<>());
			maxNode = Math.max(maxNode, node);
		}

		for (int[] edge : edges) {
			int start = edge[0];
			int end = edge[1];
			graph.get(start).add(end);
			graph.get(end).add(start);
		}

		for (int node : nodes) {
			visited = new boolean[maxNode + 1];
			checked = new boolean[maxNode + 1];
			recur(node, node);
			int[] result = getAnswer(maxNode);
			answer[0] += result[0];
			answer[1] += result[1];
		}


		return answer;
	}

	public void recur(int parent, int current) {
		List<Integer> children = graph.get(current);
		if (children.size() == 1 && visited[parent]) {
			visited[current] = true;
			if (current % 2 == 0) checked[current] = true;
			return ;
		}
		visited[current] = true;

		int count = parent == current ? children.size() : children.size() - 1;
		for (int child : children) {
			if (visited[child]) continue;
			recur(current, child);
		}

		if (current % 2 == 0 && count % 2 == 0) {
			checked[current] = true;
		} else if (current % 2 == 1 && count % 2 == 1) {
			checked[current] = true;
		}
	}

	public int[] getAnswer(int maxNode) {
		int[] answer = new int[2];
		int count = 0;
		int evenOdd = 0;
		int reverseEvenOdd = 0;
		for (int i = 1; i <= maxNode; i++) {
			if (!visited[i]) continue;
			if (checked[i]) {
				evenOdd += 1;
			} else {
				reverseEvenOdd += 1;
			}
			count += 1;
		}

		if (count == evenOdd) answer[0] = 1;
		else if (count == reverseEvenOdd) answer[1] = 1;

		return answer;
	}
}
