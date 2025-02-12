package simulation.level2;

public class PGS_행렬_테두리_회전하기 {
	int[][] map;
	int n, m;
	public int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		n = rows;
		m = columns;
		map = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				map[i][j] = (i - 1) * m + j;
			}
		}

		int index = 0;
		for (int[] query : queries) {
			answer[index++] = move(query[0], query[1], query[2], query[3]);
		}
		return answer;
	}

	public int move(int startY, int startX, int targetY, int targetX) {
		int curY = startY;
		int curX = startX;
		int elem = map[curY][curX];
		int minElem = elem;
		// 왼 -> 오
		while (curX < targetX) {
			curX += 1;
			int nextElem = map[curY][curX];
			map[curY][curX] = elem;
			elem = nextElem;
			minElem = Math.min(minElem, elem);
		}

		// 위 -> 아래
		while (curY < targetY) {
			curY += 1;
			int nextElem = map[curY][curX];
			map[curY][curX] = elem;
			elem = nextElem;
			minElem = Math.min(minElem, elem);
		}

		// 오 -> 왼
		while (curX > startX) {
			curX -= 1;
			int nextElem = map[curY][curX];
			map[curY][curX] = elem;
			elem = nextElem;
			minElem = Math.min(minElem, elem);
		}

		// 아래 -> 위
		while (curY > startY) {
			curY -= 1;
			int nextElem = map[curY][curX];
			map[curY][curX] = elem;
			elem = nextElem;
			minElem = Math.min(minElem, elem);
		}

		return minElem;
	}
}
