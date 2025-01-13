package prefix_sum.level3;

public class PGS_파괴되지_않은_건물 {
	// 정답 봄
		public int solution(int[][] board, int[][] skill) {
			int ySize = board.length;
			int xSize = board[0].length;
			int[][] prefixSum = new int[ySize + 1][xSize + 1];

			for (int[] info : skill) {
				int type = info[0];
				int startY = info[1];
				int startX = info[2];
				int targetY = info[3];
				int targetX = info[4];
				int degree = info[5];

				int effect = (type == 1) ? -degree : degree;

				prefixSum[startY][startX] += effect;
				prefixSum[startY][targetX + 1] -= effect;
				prefixSum[targetY + 1][startX] -= effect;
				prefixSum[targetY + 1][targetX + 1] += effect;
			}

			for (int i = 0; i < ySize; i++) {
				for (int j = 1; j < xSize; j++) {
					prefixSum[i][j] += prefixSum[i][j - 1];
				}
			}

			for (int j = 0; j < xSize; j++) {
				for (int i = 1; i < ySize; i++) {
					prefixSum[i][j] += prefixSum[i - 1][j];
				}
			}

			int answer = 0;
			for (int i = 0; i < ySize; i++) {
				for (int j = 0; j < xSize; j++) {
					board[i][j] += prefixSum[i][j];
					if (board[i][j] > 0) {
						answer += 1;
					}
				}
			}

			return answer;
		}
}
