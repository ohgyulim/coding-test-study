package simulation.level2;

import java.util.*;

public class PGS_빛의_경로_사이클 {
	int[] dy = {1, 0, -1, 0}; // 남서북동
	int[] dx = {0, -1, 0, 1};

	public int[] solution(String[] grid) {
		int y = grid.length;
		int x = grid[0].length();

		List<Integer> results = new ArrayList<>();
		boolean[][][] visited = new boolean[y][x][4];

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				for (int dir = 0; dir < 4; dir++) {
					if (!visited[i][j][dir]) {
						int count = calc(grid, visited, i, j, dir);
						results.add(count);
					}
				}
			}
		}

		Collections.sort(results);
		int[] answer = new int[results.size()];
		int index = 0;
		for (int res : results) {
			answer[index++] = res;
		}

		return answer;
	}

	int calc(String[] grid, boolean[][][] visited, int startY, int startX, int startDir) {
		int y = grid.length;
		int x = grid[0].length();
		int curY = startY;
		int curX = startX;
		int dir = startDir;

		int count = 0;
		while (!visited[curY][curX][dir]) {
			visited[curY][curX][dir] = true;
			count += 1;

			switch (grid[curY].charAt(curX)) {
				case 'L': {
					dir = (dir + 3) % 4;
					break;
				}
				case 'R': {
					dir = (dir + 1) % 4;
					break;
				}
			}

			int nextY = (curY + dy[dir] + y) % y;
			int nextX = (curX + dx[dir] + x) % x;

			curY = nextY;
			curX = nextX;
		}

		return count;
	}
}
