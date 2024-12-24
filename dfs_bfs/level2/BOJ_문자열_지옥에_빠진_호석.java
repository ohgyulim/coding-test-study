package dfs_bfs.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_문자열_지옥에_빠진_호석 {
	static int[] dy = {-1, 1, 0, 0, -1, 1, 1, -1};
	static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
	static int N, M;
	static char[][] matrix;
	static Map<String, Integer> map = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		matrix = new char[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				matrix[i][j] = line.charAt(j - 1);
			}
		}

		List<String> words = new ArrayList<>();
		for (int i = 1; i <= K; i++) {
			words.add(br.readLine());
		}

		for(int r = 1; r <= 5; r++){
			for(int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++)
					dfs(i, j, r, 1, String.valueOf(matrix[i][j]));
			}
		}

		for (String word : words) {
			System.out.println(map.getOrDefault(word, 0));
		}
	}

	private static void dfs(int y, int x, int r, int depth, String str) {
		if (depth == r) {
			map.put(str, map.getOrDefault(str, 0 ) + 1);
			return;
		}

		for(int i = 0; i < 8; i++) {
			// 인덱스 0 기준으로 연산하기 위해 -1
			// y + dy[i] - 1 이 음수가 나올 수 있으므로 + N 해줌
			// 인덱스 1 기준으로 되돌리기 위해 + 1
			int nextY = (y + dy[i] - 1 + N) % N + 1;
			int nextX = (x + dx[i] - 1 + M) % M + 1;

			dfs(nextY, nextX, r, depth + 1, str + matrix[nextY][nextX]);
		}
	}
}
