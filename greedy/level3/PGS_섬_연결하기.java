package greedy.level3;

import java.util.*;

public class PGS_섬_연결하기 {
	class UnionFind {
		int[] arr;
		UnionFind(int n) {
			arr = new int[n];
			for (int i = 0; i < n; i++) arr[i] = i;
		}

		public int find(int n) {
			if (arr[n] == n) {
				return n;
			}

			arr[n] = find(arr[n]);
			return arr[n];
		}

		public boolean union(int n, int m) {
			int rootN = find(n);
			int rootM = find(m);

			if (rootN == rootM) return false;

			if (rootN < rootM) arr[rootM] = rootN;
			else arr[rootN] = rootM;

			return true;
		}
	}

	class Node {
		int start;
		int target;
		int cost;

		Node(int start, int target, int cost) {
			this.start = start;
			this.target = target;
			this.cost = cost;
		}
	}

	public int solution(int n, int[][] costs) {
		int answer = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		UnionFind uf = new UnionFind(n);
		for (int[] cost : costs) {
			pq.offer(new Node(cost[0], cost[1], cost[2]));
		}

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (uf.union(node.start, node.target)) {
				answer += node.cost;
			}
		}

		return answer;
	}
}
