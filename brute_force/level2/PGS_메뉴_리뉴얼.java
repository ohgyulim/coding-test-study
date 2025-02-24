package brute_force.level2;

import java.util.*;

public class PGS_메뉴_리뉴얼 {
	Map<Integer, Map<String, Integer>> map = new HashMap<>();
	int n = 0;
	int maxLen;
	public String[] solution(String[] orders, int[] course) {
		for (int elem : course) {
			map.put(elem, new HashMap<>());
			maxLen = elem;
		}
		for (int i = 0; i < orders.length; i++) {
			char[] temp = orders[i].toCharArray();
			Arrays.sort(temp);
			orders[i] = new String(temp);
			n = temp.length;
			boolean[] alpha = new boolean[26];
			int index = 0;
			for (char ch = temp[0]; ch <= temp[n - 1]; ch++) {
				if (ch == temp[index]) {
					alpha[ch - 'A'] = true;
					index += 1;
				}
			}
			recur(alpha, i + 1, "", temp[0], temp[n - 1]);
		}

		List<String> answer = new ArrayList<>();
		for (int elem : course) {
			List<String> keySet = new ArrayList<>(map.get(elem).keySet());
			keySet.sort((o1, o2) -> map.get(elem).get(o2) - map.get(elem).get(o1));
			if (keySet.isEmpty()) continue;
			int maxCount = 2;
			for (String key : keySet) {
				if (map.get(elem).get(key) < maxCount) break;
				maxCount = map.get(elem).get(key);
				answer.add(key);
			}
		}

		Collections.sort(answer);
		String[] ans = new String[answer.size()];
		int index = 0;
		for (String elem : answer) {
			ans[index++] = elem;
		}

		return ans;
	}

	public void recur(boolean[] alpha, int count, String str, char start, char end) {
		int len = str.length();
		if (map.containsKey(len)) {
			if (map.get(len).containsKey(str)) {
				if (map.get(len).get(str) < count) map.get(len).put(str, map.get(len).get(str) + 1);
			} else map.get(len).put(str, 1);
		}
		if (str.length() == maxLen) {
			return;
		}
		for (char ch = start; ch <= end; ch++) {
			String next = str + Character.toString(ch);
			if (!alpha[ch - 'A']) continue;
			recur(alpha, count, next, (char)(ch + 1), end);
		}
	}
}
