import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        // 문제중 최대 알고력, 코딩력 값
        int maxAlp = 0;
        int maxCop = 0;

        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        // 초기 알고력, 코딩력이 주어진 최대 알고력, 코딩력을 초과하지 않도록 제한
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        int[][] dp = new int[maxAlp + 1][maxCop + 1];

        for (int i = 0; i <= maxAlp; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[alp][cop] = 0;

        // DP로 최소 시간을 계산
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                // 알고력과 코딩력 학습
                if (i < maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if (j < maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }

                // 문제 풀이
                for (int[] problem : problems) {
                    int alpReq = problem[0];
                    int copReq = problem[1];
                    int alpRwd = problem[2];
                    int copRwd = problem[3];
                    int cost = problem[4];

                    if (i >= alpReq && j >= copReq) {
                        int nextAlp = Math.min(i + alpRwd, maxAlp);
                        int nextCop = Math.min(j + copRwd, maxCop);

                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + cost);
                    }
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
}
