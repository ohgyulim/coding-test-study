package bit_masking.level2;

import java.util.*;

public class PGS_후보키 {
	public int solution(String[][] relation) {
		int colSize = relation[0].length;
		int events = 1 << colSize;
		List<Integer> answers = new ArrayList<>();
		next: for (int state = 1; state < events; state++) {
			for (int answer : answers) {
				// answer가 state의 부분집합이면 다음 state로
				if ((answer & state) == answer) continue next;
			}

			if (isAnswer(state, relation)) {
				answers.add(state);
			}
		}

		return answers.size();
	}

	public boolean isAnswer(int state, String[][] relation) {
		int n = relation.length;
		int index = 0;
		StringBuilder[] rows = new StringBuilder[n];
		for (int i = 0; i < n; i++) rows[i] = new StringBuilder();

		while (state != 0) {
			if ((state & 1) == 1) {
				for (int i = 0; i < n; i++) {
					rows[i].append(relation[i][index]);
				}
			}
			state >>= 1;
			index += 1;
		}

		Set<String> set = new HashSet<>();
		for (StringBuilder row : rows) {
			if (!set.add(row.toString())) return false;
		}

		return true;
	}
}
