package dp.level3;

import java.util.*;

public class PGS_인사고과 {

    public int solution(int[][] scores) {
        int answer = 0;
        int[] mx_height = new int[100001];

        int[] wanho = scores[0];

        for (int[] score : scores) {
            mx_height[score[0]] = Math.max(mx_height[score[0]], score[1]);
        }

        int[] dp = new int[100001];
        for (int i = 99999; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], mx_height[i + 1]);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0] + b[1], a[0] + a[1]));
        for (int[] score : scores) {
            if (score[1] >= dp[score[0]]) {
                pq.offer(score);
            }
        }

        while (!pq.isEmpty()) {
            int[] score = pq.poll();
            if (score[0] == wanho[0] && score[1] == wanho[1]) {
                return answer + 1;
            }

            if (score[0] + score[1] > wanho[0] + wanho[1]) {
                answer++;
            }
        }
        return -1;
    }
}
