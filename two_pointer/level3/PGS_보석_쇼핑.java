package two_pointer.level3;

import java.util.*;

public class PGS_보석_쇼핑 {
	public int[] solution(String[] gems) {
		int n = gems.length;

		Set<String> uniqueGems = new HashSet<>(Arrays.asList(gems));
		int totalCnt = uniqueGems.size();

		if (uniqueGems.size() == totalCnt) return new int[]{1, totalCnt};

		Map<String, Integer> map = new HashMap<>();
		int left = 0, right = 0;
		int minLength = n + 1;
		int ansL = 0, ansR = 0;

		while (right < n) {
			map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
			right += 1;

			while (map.size() == totalCnt) {
				if (right - left < minLength) {
					minLength = right - left;
					ansL = left + 1;
					ansR = right;
				}
				map.put(gems[left], map.get(gems[left]) - 1);
				if (map.get(gems[left]) == 0) {
					map.remove(gems[left]);
				}
				left += 1;
			}
		}

		return new int[]{ansL, ansR};
	}
}
