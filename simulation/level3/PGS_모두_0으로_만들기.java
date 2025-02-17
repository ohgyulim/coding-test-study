package simulation.level3;

import java.util.*;

public class PGS_모두_0으로_만들기 {
	List<Integer>[] graph;
	int n;
	long[] weights;
	public long solution(int[] a, int[][] edges) {
		n = a.length;
		graph = new ArrayList[n];
		weights = new long[n];

		long sum = 0;
		for (int i = 0; i < n; i++) {
			sum += a[i];
			weights[i] = (long)a[i];
			graph[i] = new ArrayList<>();
		}

		if (sum != 0) return -1;

		for (int[] edge : edges) {
			int from = edge[0];
			int to = edge[1];
			graph[from].add(to);
			graph[to].add(from);
		}

		return recur(0);
	}

	public long recur(int root) {
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		stack.push(root);

		boolean[] visited = new boolean[n];
		visited[root] = true;

		while (!stack.isEmpty()) {
			int parent = stack.pop();

			List<Integer> nodes = graph[parent];
			for (int child : nodes) {
				if (!visited[child]) {
					visited[child] = true;
					stack.push(child);
					stack2.push(child);
				}
			}
		}

		visited = new boolean[n];
		long w = 0;
		while(!stack2.isEmpty()) {
			int child = stack2.pop();
			long weight = weights[child];
			List<Integer> nodes = graph[child];
			visited[child] = true;
			for (int parent : nodes) {
				if (!visited[parent]) {
					weights[parent] += weight;
				}
			}
			w += Math.abs(weight);
		}

		return w;

	}
}
