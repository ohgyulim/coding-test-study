package binary_search.level3;

public class PGS_금과_은_운반하기 {
	public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
		long answer = -1;
		int n = g.length;
		long left = 1;
		long right = (long)(10e9 * 10e5) * 2L * 2L; // 가능한 시간 최댓값 = 개수 최대 * 시간 최대 * 왕복 * 미네랄 개수
		// 시간을 이분탐색하며 결과를 구한다
		while (left <= right) {
			long mid = (left + right) / 2L;

			int gold = 0;
			int silver = 0;
			int sum = 0;
			for (int i = 0; i < n; i++) {
				int weight = w[i];
				int time = t[i];
				// 정해진 시간(mid) 내에서 이동 가능 횟수 구하기
				// 1. 왕복 가능 횟수
				long count = mid / (time * 2);

				// 2. 추가적으로 편도가 가능하면
				if((mid % (time * 2)) >= time){
					count += 1;
				}

				// count = 왕복(왕복해도 weight 만큼만 옮길 수 있음) + 편도
				// (i번째 도시가 갖고 있는 미네랄의 양)보다 (왔다갔다 하면서 옮길 수 있는 양)이 크면 안되므로!
				gold += Math.min(g[i], weight * count);
				silver += Math.min(s[i], weight * count);
				sum += Math.min(g[i] + s[i], weight * count);
			}

			if (gold >= a && silver >= b && sum >= a + b) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return answer;
	}
}
