package dp.level2;

public class LC_Maximum_Subarray {
	public int maxSubArray(int[] nums) {
		int dp[] = new int[nums.length];
		dp[0] = nums[0];
		int answer = dp[0];
		for (int i = 1; i < nums.length; i++) {
			dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
			answer = Math.max(answer, dp[i]);
		}

		return answer;
	}
}
