package dfs_bfs.level3;

public class PGS_네트워크 {
	boolean[] visited;
	public int solution(int n, int[][] computers) {
		visited = new boolean[n];

		int count = 0;
		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			recur(i, computers);
			count += 1;
		}

		return count;
	}


	public void recur(int node, int[][] computers) {
		if (visited[node]) return;
		visited[node] = true;
		int[] children = computers[node];

		for (int index = 0; index < children.length; index++) {
			int child = children[index];
			if (child == 0 || visited[index]) continue;
			recur(index, computers);
		}
	}
}
