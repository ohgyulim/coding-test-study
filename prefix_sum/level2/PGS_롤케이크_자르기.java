package prefix_sum.level2;

// 누적합 문제
// chulsu와 brother 배열 계산 부분을 누적합으로 바꿔서 푸는 게 더 효율적
public class PGS_롤케이크_자르기 {
	public int solution(int[] topping) {
		int answer = 0;
		int size = topping.length;
		boolean[] chulsuTakesTopping = new boolean[10_001];
		boolean[] broTakesTopping = new boolean[10_001];
		int[] chulsu = new int[size + 1];
		int[] brother = new int[size + 1];

		for (int i = 1; i < size; i++) {
			chulsu[i] = chulsu[i-1];
			if (!chulsuTakesTopping[topping[i - 1]]) {
				chulsuTakesTopping[topping[i - 1]] = true;
				chulsu[i] += 1;
			}

			brother[size - i] = brother[size - i + 1];
			if (!broTakesTopping[topping[size - i]]) {
				broTakesTopping[topping[size - i]] = true;
				brother[size - i] += 1;
			}
		}

		for (int i = 1; i < topping.length; i++) {
			if (chulsu[i] != brother[i]) continue;
			answer += 1;
		}

		return answer;
	}
}
