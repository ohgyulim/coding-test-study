package simulation.level2;

public class PGS_삼각_달팽이 {
	static int[][] map;
	public int[] solution(int n) {
		if (n == 1) return new int[]{1};
		int[] answer = new int[(n + 1) * n / 2];
		map = new int[n + 1][n + 1];
		for (int i = 1; i < n; i += 2) {
			setNum(i, n);
		}

		int index = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (map[i][j] == 0) continue;
				answer[index++] = map[i][j];
			}
		}
		return answer;
	}

	public void setNum(int startY, int n) {
		int startX = (startY/2) + 1;
		int endY = n - (startX - 1);
		int endX = endY - (startX - 1);

		for (int y = startY; y <= endY; y++) {
			map[y][startX] = map[y - 1][startX] + 1;
		}

		for (int x = startX + 1; x <= endX; x++) {
			map[endY][x] = map[endY][x - 1] + 1;
		}

		while (endY > startY + 1) {
			map[endY - 1][endX - 1] = map[endY][endX] + 1;
			endY -= 1;
			endX -= 1;
		}
	}
}
