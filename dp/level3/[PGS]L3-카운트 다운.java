import java.util.*;

class Solution {
    public int[] solution(int target) {
        //dp[i][0] = i점수까지의 최소 다트 수
        //dp[i][1] = i점수까지의 싱글/불
        int[][] dp = new int[target + 1][2];

        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = 0;
        }

        int[] scores = new int[61];
        int index = 0;
        for (int i = 1; i <= 20; i++) {
            scores[index++] = i;
            scores[index++] = i * 2;
            scores[index++] = i * 3;
        }
        //불
        scores[index] = 50;

        for (int i = 0; i <= target; i++) {
            for (int score : scores) {
                if (i - score >= 0) {
                    //현재 다트수
                    int darts = dp[i - score][0] + 1;
                    // 싱글/불 개수
                    int singles = dp[i - score][1] + ((score <= 20 || score == 50) ? 1 : 0);

                    // 더 좋은 방법 있으면 업데이트
                    if (darts < dp[i][0] || (darts == dp[i][0] && singles > dp[i][1])) {
                        dp[i][0] = darts;
                        dp[i][1] = singles;
                    }
                }
            }
        }

        return new int[]{dp[target][0], dp[target][1]};
    }
}