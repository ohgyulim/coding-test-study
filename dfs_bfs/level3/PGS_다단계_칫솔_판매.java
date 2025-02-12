package dfs_bfs.level3;

import java.util.*;

public class PGS_다단계_칫솔_판매 {
	class Person {
		String name = null;
		Person parent = null;
		int money = 0;
		Person(String name, Person parent) {
			this.name = name;
			this.parent = parent;
		}

		void setMoney(int money) {
			if (money == 0) return;

			int parentMoney = (int)(money * 0.1);
			this.money += (money - parentMoney);
			if (parent != null) {
				this.parent.setMoney(parentMoney);
			}
		}
	}
	public int[] solution(String[] enrolls, String[] referrals, String[] sellers, int[] amounts) {
		int n = enrolls.length;
		int[] answer = new int[n];

		Map<String, Person> relations = new HashMap<>();
		for (String enroll : enrolls) {
			relations.put(enroll, new Person(enroll, null));
		}

		for (int i = 0; i < n; i++) {
			String enroll = enrolls[i];
			String referral = referrals[i];

			if (!referral.equals("-")) {
				// 기존 객체를 연결해서 부모, 조부모 ... root 까지 객체 연결하기
				relations.get(enroll).parent = relations.get(referral);
			}
		}

		int m = sellers.length;
		for (int i = 0; i < m; i++) {
			Person seller = relations.get(sellers[i]);
			int money = amounts[i] * 100;
			seller.setMoney(money);
		}

		int index = 0;
		for (String enroll : enrolls) {
			answer[index++] = relations.get(enroll).money;
		}

		return answer;
	}
}
