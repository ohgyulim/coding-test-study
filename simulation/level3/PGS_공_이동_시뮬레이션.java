package simulation.level3;

public class PGS_공_이동_시뮬레이션 {
	public long solution(int n, int m, int x, int y, int[][] queries) {
		long endY = x; // x <-> y 의미 변경 (y: 위아래, x: 좌우)
		long endX = y;
		long startY = x;
		long startX = y;

		for (int i = queries.length - 1; i >= 0; i--) {
			int dir = queries[i][0];
			int amount = queries[i][1];

			if (dir == 0) { // 오른쪽으로 확장
				if (startX != 0) startX += amount;
				endX = Math.min(endX + amount, m - 1);
				if (startX > m - 1) return 0;
			} else if (dir == 1) { // 왼쪽으로 확장
				if (endX != m-1) endX -= amount;
				startX = Math.max(startX - amount, 0);
				if (endX < 0) return 0;
			} else if (dir == 2) { // 아래로 확장
				if (startY != 0) startY += amount;
				endY = Math.min(endY + amount, n - 1);
				if (startY > n - 1) return 0;
			} else { // 위로 확장
				if (endY != n-1) endY -= amount;
				startY = Math.max(startY - amount, 0);
				if (endY < 0) return 0;
			}
		}

		return (endY - (startY - 1)) * (endX - (startX - 1));
	}
}
