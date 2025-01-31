package brute_force.level2;

import java.util.*;

public class PGS_모음사전 {
	char[] alphas = new char[]{'A', 'E', 'I', 'O', 'U'};
	Set<String> words = new TreeSet<>();
	public int solution(String word) {
		int answer = 0;
		recur("");
		for (String str : words) {
			if (str.equals(word)) break;
			answer += 1;
		}

		return answer;
	}

	public void recur(String str) {
		if (str.length() > 5) {
			return;
		}
		words.add(str);

		for (int i = 0; i < 5; i++) {
			String curStr = str + alphas[i];
			recur(curStr);
		}
	}
}
