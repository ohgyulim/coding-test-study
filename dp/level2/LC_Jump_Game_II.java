package dp.level2;
import java.util.*;

public class LC_Jump_Game_II {
	public int jump(int[] nums) {
		int[] dp = new int[nums.length];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i < nums.length; i++) {
			int step = nums[i];
			for (int s = 0; s <= step; s++) {
				if (i + s < nums.length) {
					dp[i + s] = Math.min(dp[i + s], dp[i] + 1);
				}
			}
		}
		return dp[nums.length - 1];
	}
}
