package dfs_bfs.level3;

import java.util.*;

public class PGS_다단계_칫솔_판매 {
	Map<String, String> relations = new HashMap<>();
	Map<String, Integer> moneys = new HashMap<>();
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amounts) {
		int n = enroll.length;
		for (int i = 0; i < n; i++) {
			String name = enroll[i];
			String parent = referral[i];
			relations.put(name, parent);
			moneys.put(name, 0);
		}
		relations.put("-", null);
		moneys.put("-", 0);

		int m = seller.length;
		for (int i = 0; i < m; i++) {
			String name = seller[i];
			int amount = amounts[i];
			calc(name, amount);
		}

		int[] answer = new int[n];
		int index = 0;
		for (String name : enroll) {
			answer[index++] = moneys.get(name);
		}

		return answer;
	}

	public void calc(String seller, int amount) {
		int money = amount * 100;

		Queue<String> queue = new LinkedList<>();
		queue.offer(seller);
		moneys.put(seller, moneys.get(seller) + money);

		while(!queue.isEmpty()) {
			if (money == 0) break;
			String person = queue.poll();

			money = (int)(money * 0.1);
			moneys.put(person, moneys.get(person) - money);

			String parent = relations.getOrDefault(person, null);
			if (parent != null) {
				moneys.put(parent, moneys.get(parent) + money);
				queue.offer(parent);
			}
		}
	}
}
