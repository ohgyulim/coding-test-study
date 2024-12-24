package dp.level2;

import java.util.*;

public class PGS_우박수열정적분 {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        Integer[] collatzArray = collatz(k);
        int n = collatzArray.length - 1;
        double[] dp = new double[n];

        for (int i = 0; i < ranges.length; i++) {
            int left = ranges[i][0];
            int right = n + ranges[i][1];
            if (left > right) {
                answer[i] = -1;
                continue;
            }
            double intervalSum = 0;
            for (int x = left; x < right; x++) {
                if (dp[x] != 0) {
                    intervalSum += dp[x];
                    continue;
                }
                int leftY = collatzArray[x];
                int rightY = collatzArray[x + 1];
                int y = Math.max(leftY, rightY);
                dp[x] = y - Math.abs(leftY - rightY) / (double) 2;
                intervalSum += dp[x];
            }
            answer[i] = intervalSum;
        }

        return answer;
    }

    private Integer[] collatz(int k) {
        List<Integer> collatzArray = new ArrayList<>();
        while (k != 1) {
            collatzArray.add(k);
            if (k % 2 == 0) {
                k = k / 2;
            } else {
                k = k * 3 + 1;
            }
        }
        collatzArray.add(k);
        return collatzArray.toArray(new Integer[0]);
    }
}
