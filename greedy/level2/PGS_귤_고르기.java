package greedy.level2;

import java.util.*;

public class PGS_귤_고르기 {
	public int solution(int k, int[] tangerine) {
		int answer = 0;

		Map<Integer, Integer> tangerineCount = new HashMap<>();
		for(int t : tangerine) {
			tangerineCount.put(t, tangerineCount.getOrDefault(t, 0) + 1);
		}

		List<Integer> tangerines = new ArrayList<>(tangerineCount.values());
		Collections.sort(tangerines, Collections.reverseOrder());

		for (int t : tangerines) {
			if (k <= 0) break;
			k -= t;
			answer += 1;
		}

		return answer;
	}
}
