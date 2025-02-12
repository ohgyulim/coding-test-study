package binary_search.level3;

import java.util.*;

public class BOJ_중량제한 {
	static int N, M;
	static List<List<Edge>> graph = new ArrayList<>();
	static int start, end;

	static class Edge {
		int to, weight;
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		int maxWeight = 0;
		for (int i = 0; i < M; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int C = sc.nextInt();
			graph.get(A).add(new Edge(B, C));
			graph.get(B).add(new Edge(A, C));
			maxWeight = Math.max(maxWeight, C);
		}

		start = sc.nextInt();
		end = sc.nextInt();

		System.out.println(binarySearch(maxWeight));
	}

	static int binarySearch(int maxWeight) {
		int left = 1, right = maxWeight, answer = 0;

		while (left <= right) {
			int mid = (left + right) / 2;
			if (canCross(mid)) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return answer;
	}

	static boolean canCross(int weightLimit) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		queue.add(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (cur == end) return true;

			for (Edge edge : graph.get(cur)) {
				if (!visited[edge.to] && edge.weight >= weightLimit) {
					visited[edge.to] = true;
					queue.add(edge.to);
				}
			}
		}
		return false;
	}
}
