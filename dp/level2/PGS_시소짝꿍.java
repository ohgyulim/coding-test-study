package dp.level2;

import java.util.*;

public class PGS_시소짝꿍 {
    public long solution(int[] weights) {
        long answer = 0;
        int n = weights.length;
        int[] dp = new int[4001];
        int[] distances = {2, 3, 4};
        Arrays.sort(weights);
        dp[weights[0] * 2] = 1;
        dp[weights[0] * 3] = 1;
        dp[weights[0] * 4] = 1;

        int prevWeight = weights[0];
        int equalCnt = 0;
        for (int i = 1; i < n; i++) {
            if (prevWeight == weights[i]) {
                equalCnt++;
                answer += equalCnt;
            } else {
                prevWeight = weights[i];
                equalCnt = 0;
            }
            for (int distance : distances) {
                int newton = weights[i] * distance;
                answer += dp[newton] - equalCnt;
                dp[newton]++;
            }
        }
        return answer;
    }
}

// dp[i] 에는 weights를 반복 돌면서(j) weights[j]값들을 2배,3배,4배해서 i값이 나온 weights[j]의 수
// 즉 weigths를 반복 돌면서 weights[j]값을 2배,3배,4배 해서 나온 값이 k일 때 dp[k]의 개수만큼 weights[j]는 짝꿍이 있는거임
// 근데 weights[j]가 중복될 수 있는데 이 경우엔 중복값들이 이미 dp에 반영이 되어 있으므로 중복 개수 만큼만 더해주고 + weights[j]가 다른 값에 의해서 같은 값이 된경우 만큼 +