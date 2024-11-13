package dp.level3;

class PGS_아방가르드_타일링 {
    public int solution(int n) {
        int answer = 0;
        long[] dp = new long[n + 1];
        dp[0] = 1L;
        dp[1] = 1L;
        if (n >= 2) {
            dp[2] = 3L;
        }

        for (int i = 3; i < n + 1; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] * 2 + dp[i - 3] * 5) % 1000000007;
            if (i >= 4) {
                int j = i - 4;
                while (j >= 0) {
                    dp[i] += dp[j] * 2;
                    j -= 3;
                }
            }
            if (i >= 5) {
                int j = i - 5;
                while (j >= 0) {
                    dp[i] += dp[j] * 2;
                    j -= 3;
                }
            }
            if (i >= 6) {
                int j = i - 6;
                while (j >= 0) {
                    dp[i] += dp[j] * 4;
                    j -= 3;
                }
            }
            dp[i] %=  1000000007;
        }
        answer = (int)dp[n];
        return answer;
    }
}

