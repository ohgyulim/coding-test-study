package dp.level2;

public class PGS_숫자변환하기 {
    public int solution(int x, int y, int n) {
        int answer = 0;
        int[] dp = new int[y + 1];
        for (int i = 0; i <= y; i++) {
            dp[i] = -1;
        }
        dp[x] = 0;
        for (int i = x; i <= y; i++) {
            int mn = 1000000;
            if (i - n >= 0 && dp[i - n] != -1) {
                mn = dp[i - n] + 1;
            }
            if (i % 2 == 0 && dp[i / 2] != -1) {
                mn = Math.min(mn, dp[i / 2] + 1);
            }
            if (i % 3 == 0 && dp[i / 3] != -1) {
                mn = Math.min(mn, dp[i / 3] + 1);
            }
            if (mn != 1000000) {
                dp[i] = mn;
            }
        }
        answer = dp[y];
        return answer;
    }
}
