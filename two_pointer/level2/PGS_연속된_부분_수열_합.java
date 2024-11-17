package two_pointer.level2;

public class PGS_연속된_부분_수열_합 {
	public int[] solution(int[] sequence, int k) {
		if (sequence[0] == k) return new int[]{0, 0};

		int[] answer = new int[2];
		int[] sums = new int[sequence.length];
		int length = 1_000_001;
		int start = 0;

		sums[0] = sequence[0];

		for (int i = 1; i < sequence.length; i++) {
			sums[i] = sums[i-1] + sequence[i];

			while (sums[i] > k) {
				sums[i] -= sequence[start];
				start += 1;
			}

			if (sums[i] == k) {
				if (length > i - start) {
					length = i - start;
					answer[0] = start;
					answer[1] = i;
				}
			}
		}

		return answer;
	}
}
