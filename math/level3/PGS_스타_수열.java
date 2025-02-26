package math.level3;

public class PGS_스타_수열 {
	// 정답 봄
	public int solution(int[] a) {
		int answer = 0;
		int[] cnt = new int[a.length];
		for (int elem : a) {
			cnt[elem] += 1;
		}

		for (int i = 0; i < cnt.length; i++) {
			if (cnt[i] <= answer) continue;

			int ans = 0;
			for (int j = 0; j < a.length - 1; j++) {
				if (a[j] != a[j + 1] && (i == a[j] || i == a[j + 1])) {
					ans += 1;
					j += 1;
				}
			}

			answer = Math.max(answer, ans);
		}

		return answer * 2;
	}
}
