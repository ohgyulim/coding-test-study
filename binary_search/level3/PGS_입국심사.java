package binary_search.level3;

public class PGS_입국심사 {
	public long solution(int n, int[] times) {
		int maxTime = 0;
		for (int time : times) maxTime = Math.max(maxTime, time);

		long left = 1;
		long right = (long)maxTime * (long)n;
		long answer = 0;
		while (left <= right) {
			long mid = (left + right) / 2;

			long count = 0;
			for (int time : times) {
				count += (mid / time);
			}

			if (count >= n) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return answer;
	}
}
