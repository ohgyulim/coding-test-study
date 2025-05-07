package backtracking.level3;

import java.util.*;

public class PGS_불량사용자 {
	Set<Set<Integer>> cases = new HashSet<>();
	List<List<Integer>> candidates = new ArrayList<>();
	public int solution(String[] user_ids, String[] banned_ids) {
		for (String banned_id : banned_ids) {
			List<Integer> candidate = new ArrayList<>();
			for (int i = 0; i < user_ids.length; i++) {
				String user_id = user_ids[i];
				if (isMatched(banned_id, user_id)) {
					candidate.add(i);
				}
			}
			candidates.add(candidate);
		}

		recur(banned_ids.length, new HashSet<>(), 0);

		int answer = cases.size();
		return answer;
	}

	public void recur(int n, Set<Integer> set, int index) {
		if (index == n) {
			cases.add(new HashSet<>(set));
			return;
		}

		List<Integer> candidate = candidates.get(index);
		for (int userIndex : candidate) {
			if (!set.contains(userIndex)) {
				set.add(userIndex);
				recur(n, set, index + 1);
				set.remove(userIndex);
			}
		}


	}

	public boolean isMatched(String banned_id, String user_id) {
		if (banned_id.length() != user_id.length()) return false;

		for (int i = 0; i < banned_id.length(); i++) {
			if (banned_id.charAt(i) == '*') continue;
			if (banned_id.charAt(i) != user_id.charAt(i)) return false;
		}

		return true;
	}
}
