package dp.level2;

public class LC_Edit_Distance {
	public int minDistance(String word1, String word2) {
		int srcLength = word1.length();
		int destLength = word2.length();
		int[][] dp = new int[srcLength + 1][destLength + 1];

		for (int y = 1; y <= srcLength; y++) {
			dp[y][0] = y;
		}
		for (int x = 1; x <= destLength; x++) {
			dp[0][x] = x;
		}

		for (int y = 1; y <= srcLength; y++) {
			for (int x = 1; x <= destLength; x++) {
				if (word1.charAt(y-1) == word2.charAt(x-1)) {
					dp[y][x] = dp[y-1][x-1];
					continue;
				}
				dp[y][x] = Math.min(dp[y-1][x-1], Math.min(dp[y-1][x], dp[y][x-1])) + 1;
			}
		}
		return dp[srcLength][destLength];
	}
}
