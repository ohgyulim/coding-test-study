package simulation.level2;

public class PGS_두_큐_합_같게_만들기 {
	public int solution(int[] queue1, int[] queue2) {
		int answer = 0;
		long queue1Sum = 0;
		long queue2Sum = 0;
		long totalSum = 0;

		int n = queue1.length;

		for (int i = 0; i < n; i++) {
			queue1Sum += queue1[i];
			queue2Sum += queue2[i];
		}
		totalSum = queue1Sum + queue2Sum;

		if (totalSum % 2 != 0) return -1;
		long targetSum = totalSum / 2;

		int[] totalQueue = new int[n * 2];
		for (int i = 0; i < n; i++) {
			totalQueue[i] = queue1[i];
			totalQueue[i + n] = queue2[i];
		}

		int left = 0;
		int right = n;

		while (left < n * 2 && right < n * 2) {
			if (queue1Sum == targetSum) {
				return answer;
			} else if (queue1Sum > targetSum) {
				queue1Sum -= totalQueue[left++];
			} else {
				queue1Sum += totalQueue[right++];
			}
			answer += 1;

			if (answer > n * 3) return -1;
		}

		return -1;
	}
}
