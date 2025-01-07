package prefix_sum.level2;

import java.util.*;

public class PGS_할인_행사 {
	int[][] dp;
	public int solution(String[] want, int[] number, String[] discount) {
		int answer = 0;
		Map<String, Integer> wantMap = new HashMap<>();
		for (int i = 0; i < want.length; i++) {
			wantMap.put(want[i], i);
		}

		dp = new int[discount.length + 1][want.length];

		for (int day = 1; day <= discount.length; day++) {
			String discountItem = discount[day - 1];
			if (!wantMap.containsKey(discountItem)) continue;

			// dp 배열 초기화
			int itemIndex = wantMap.get(discountItem);
			for (int i = 0; i < want.length; i++) {
				dp[day][i] = dp[day - 1][i];
			}
			dp[day][itemIndex] += 1;

			// 정답 판단
			if (day >= 10) {
				if (isAnswer(number, day)) answer += 1;
			}
		}

		return answer;
	}

	public boolean isAnswer(int[] number, int day) {
		for (int i = 0; i < number.length; i++) {
			int wantCount = number[i];
			int curCount = dp[day][i] - dp[day - 10][i];
			if (curCount < wantCount)
				return false;
		}
		return true;
	}
}
