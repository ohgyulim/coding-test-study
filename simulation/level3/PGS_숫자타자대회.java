package simulation.level3;

import java.util.Arrays;

// 해설 봄

public class PGS_숫자타자대회 {
    int[][][] dp;
    int len;
    String nums;

    public int[][] cost = {
            {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
            {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
            {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
            {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
            {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
            {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
            {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
            {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
            {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
            {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}
    };

    public int solution(String numbers) {
        int answer = 0;
        //최소한의 시간으로 타이핑을 하는 경우의 가중치 합을 return


        len = numbers.length();
        nums = numbers;
        dp = new int[len][10][10]; //idx, left, right

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], -1);
        }

        return solve(0, 4, 6);

    }

    public int solve(int idx, int left, int right) {
        if (idx == len) {
            return 0; //문자열 끝까지 탐색한 경우
        }
        //이미 계산된 경우
        if (dp[idx][left][right] != -1) return dp[idx][left][right];

        int num = nums.charAt(idx) - '0';
        int ans = Integer.MAX_VALUE;

        //왼쪽 손가락을 움직이는 경우
        if (num != right) {
            ans = Math.min(solve(idx + 1, num, right) + cost[left][num], ans);
        }

        //오른쪽 손가락을 움직이는 경우
        if (num != left) {
            ans = Math.min(solve(idx + 1, left, num) + cost[right][num], ans);
        }

        return dp[idx][left][right] = ans;
    }
}

