package dp.level2;

public class LC_Minimum_Path_Sum {
	public int minPathSum(int[][] grid) {
		int y = grid.length;
		int x = grid[y - 1].length;
		int[][] dp = new int[y][x];
		dp[0][0] = grid[0][0];
		for (int i = 1; i < y; i++) {
			dp[i][0] = dp[i-1][0] + grid[i][0];
		}
		for (int j = 1; j < x; j++) {
			dp[0][j] = dp[0][j-1] + grid[0][j];
		}
		for (int i = 1; i < y; i++) {
			for (int j = 1; j < x; j++) {
				dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
			}
		}
		return dp[y-1][x-1];
	}
}
