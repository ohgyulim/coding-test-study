package binary_search.level3;

public class PGS_징검다리_건너기 {
	public int solution(int[] stones, int k) {
		int answer = 0;
		int n = stones.length;
		int left = 1;
		int right = 0;
		for (int stone : stones) {
			right = Math.max(stone, right);
		}

		while (left <= right) {
			int mid = (left + right) / 2;
			if (isAnswer(stones, k, mid)) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		answer = left;

		return answer;
	}

	public boolean isAnswer(int[] stones, int k, int mid) {
		int length = 0;
		for (int stone : stones) {
			if (stone - mid <= 0) length += 1;
			else length = 0;

			if (length >= k) return false;
		}

		return true;
	}
}
