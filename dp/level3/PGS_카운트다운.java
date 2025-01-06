package dp.level3;

public class PGS_카운트다운 {
    public int[] solution(int target) {

        int[][] dp = new int[target + 1][2];
        for (int i = 1; i <= target; i++) {
            dp[i][0] = target;
        }

        for (int i = 1; i <= 20 && i <= target; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
        }

        for (int i = 21; i <= target; i++) {
            for (int j = 1; j <= 20; j++) {
                if (dp[i][0] > dp[i - j][0]) { // 싱글
                    dp[i][0] = dp[i - j][0] + 1;
                    dp[i][1] = dp[i - j][1] + 1;
                }

                if (i - j * 2 >= 0 && dp[i][0] > dp[i - j * 2][0]) { // 더블
                    dp[i][0] = dp[i - j * 2][0] + 1;
                    dp[i][1] = dp[i - j * 2][1];
                }

                if (i - j * 3 >= 0 && dp[i][0] > dp[i - j * 3][0]) { // 더블
                    dp[i][0] = dp[i - j * 3][0] + 1;
                    dp[i][1] = dp[i - j * 3][1];
                }
            }
            if (i - 50 >= 0 && dp[i][0] > dp[i - 50][0]) {
                dp[i][0] = dp[i - 50][0] + 1;
                dp[i][1] = dp[i - 50][1] + 1;
            }
        }
        return dp[target];
    }
}
