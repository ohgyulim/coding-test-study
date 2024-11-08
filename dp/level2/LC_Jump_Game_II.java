package dp.level2;

public class LC_Jump_Game_II {
    public int jump(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];

        for (int i=1;i<n;i++) {
            dp[i] = dp[i-1] + 1;
            for (int j=0;j<i;j++) {
                if (nums[j] >= i-j) {
                    dp[i] = Math.min(dp[j]+1, dp[i]);
                }
            }
        }

        return dp[n-1];
    }
}
