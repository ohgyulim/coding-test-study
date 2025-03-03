package dfs_bfs.level3;

import java.util.*;

public class PGS_경주로_건설 {
	class Position {
		int y;
		int x;
		int dir;
		int cost;
		Position(int y, int x, int dir, int cost) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.cost = cost;
		}
	}
	// 0: 북, 1: 동, 2: 남, 3: 서
	int[] dy = {-1, 0, 1, 0};
	int[] dx = {0, 1, 0, -1};
	int answer = Integer.MAX_VALUE;
	int INF = 100_000_000;
	public int solution(int[][] board) {
		bfs(board);
		return answer;
	}

	public void bfs(int[][] board) {
		int n = board.length;
		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(0, 0, -1, 0));
		int[][] visited = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) visited[i][j] = INF;
		}
		visited[0][0] = 0;

		while (!queue.isEmpty()) {
			Position cur = queue.poll();
			int curY = cur.y;
			int curX = cur.x;
			int curCost = cur.cost;
			int curDir = cur.dir;
			if (curY == n - 1 && curX == n - 1) answer = Math.min(answer, curCost);
			for (int i = 0; i < 4; i++) {
				int nextY = curY + dy[i];
				int nextX = curX + dx[i];
				if (isInRange(n, nextY, nextX) && visited[curY][curX] < visited[nextY][nextX] && board[nextY][nextX] == 0) {
					if (curDir == -1 || curDir == i) curCost += 100;
					else curCost += 600;
					visited[nextY][nextX] = Math.min(visited[nextY][nextX], curCost);
					queue.offer(new Position(nextY, nextX, i, curCost));
				}
			}
		}

	}

	public boolean isInRange(int n, int nextY, int nextX) {
		return nextY >= 0 && nextY < n && nextX >= 0 && nextX < n;
	}
}
