package dp.level3;

public class PGS_억억단을_외우자 {
	public int[] solution(int e, int[] starts) {
		int [] answer = new int[starts.length];
		int[] divisorCount = new int[e + 1];
		int[] maxCountIndex = new int[e + 1];

		for (int i = 1; i <= e; i++) {
			for (int j = i; j <= e; j += i) {
				divisorCount[j] += 1;
			}
		}

		int maxIndex = e;
		for (int i = e; i >= 1; i--) {
			if (divisorCount[i] >= divisorCount[maxIndex]) {
				maxIndex = i;
			}
			maxCountIndex[i] = maxIndex;
		}

		int index = 0;
		for (int start : starts) {
			answer[index++] = maxCountIndex[start];
		}

		return answer;
	}
}
