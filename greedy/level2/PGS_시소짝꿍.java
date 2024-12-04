package greedy.level2;

import java.util.*;

public class PGS_시소짝꿍 {
	public long solution(int[] weights) {
		long answer = 0;
		Map<Long, Long> weightCount = new HashMap<>();
		long[] dp = new long[4001];

		// weight 개수 구하기
		// dp 배열에 weight의 2배수, 3배수, 4배수 개수 초기화하기
		for (long weight : weights) {
			weightCount.put(weight, weightCount.getOrDefault(weight, 0L) + 1L);
			dp[(int) weight * 2] += 1;
			dp[(int) weight * 3] += 1;
			dp[(int) weight * 4] += 1;
		}

		long cnt = 0;

		for (var entrySet : weightCount.entrySet()) {
			long weight = entrySet.getKey();
			long count = entrySet.getValue();
			// 100, 100, 100, 100 과 같은 중복 값 존재할 때 anwer에 더하기
			// count = 4 일 때, 3 + 2 + 1 이므로 1 ~ 3까지의 합(= 1 ~ (count-1)까지 합)
			answer += (count * (count - 1)) / 2;
			for (long i = 2; i <= 4; i++) {
				int scaledWeight = (int) (weight * i);
				// dp[scaledWeight] = d, count = c 라고 할 때, d = c1 + c2 + c3 ..
				// 1. c는 (d-c)를 c번 가질 수 있음 = c * (d-c)
				// 2. d를 d-c로 초기화
				if (dp[scaledWeight] > count) {
					cnt += ((dp[scaledWeight] - count) * count);
					dp[scaledWeight] -= count;
				}
			}
		}
		return answer + cnt;
	}
}
