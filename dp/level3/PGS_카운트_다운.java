package dp.level3;

import java.util.*;

public class PGS_카운트_다운 {
	public int[] solution(int target) {
		int[][] dp = new int[100_001][2];

		for (int i = 1; i <= target; i++) dp[i] = new int[]{Integer.MAX_VALUE, 0};

		for (int i = 1; i <= 20; i++) {
			dp[i] = new int[]{1, 1};
			dp[i * 2][0] = 1;
			dp[i * 3][0] = 1;
		}
		dp[50] = new int[]{1, 1};

		for (int i = 21; i <= target; i++) {
			PriorityQueue<int[]> queue = new PriorityQueue<>(
				(o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
			for (int j = 1; j <= 60; j++) {
				if (i - j >= 1 && dp[i - j][0] + dp[j][0] <= dp[i][0]) {
					queue.offer(new int[]{dp[i - j][0] + dp[j][0], dp[i - j][1] + dp[j][1]});
					dp[i] = queue.peek();
				}
			}
		}

		return dp[target];
	}
}
