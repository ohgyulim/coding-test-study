package 최단거리.floyd_warshall.level3;

public class PGS_순위 {
	public int solution(int n, int[][] results) {
		boolean[][] graph = new boolean[n + 1][n + 1];
		for (int[] result : results) {
			int win = result[0];
			int lose = result[1];
			graph[win][lose] = true;
		}

		for (int mid = 1; mid <= n; mid++) {
			for (int win = 1; win <= n; win++) {
				for (int lose = 1; lose <= n; lose++) {
					if (graph[win][mid] && graph[mid][lose]) graph[win][lose] = true;
				}
			}
		}

		int answer = 0;
		for (int win = 1; win <= n; win++) {
			int count = 0;
			for (int lose = 1; lose <= n; lose++) {
				if (win == lose) continue;
				if (graph[win][lose] || graph[lose][win]) count += 1;
			}
			if (count == n - 1) answer += 1;
		}


		return answer;
	}
}
