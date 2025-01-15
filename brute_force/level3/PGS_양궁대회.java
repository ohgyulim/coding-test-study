package brute_force.level3;

// 1. 선택하는 경우, 선택 안하는 경우를 혼합하여 라이언 결과 배열을 만든다.
// 2. maxDiff < (라이언 결과 - 어피치 결과) 인 경우, 무조건 정답 배열을 초기화 한다
// 3. maxDiff = (라이언 결과 - 어피치 결과) 인 경우, 가장 낮은 점수가 많은 경우로 초기화 한다
public class PGS_양궁대회 {
	int maxDiff = 0;
	int[] answer = new int[11];
	public int[] solution(int n, int[] info) {
		int[] lion = new int[11];
		recur(0, n, info, lion);
		if (maxDiff == 0) return new int[]{-1};

		return answer;
	}
	void recur(int index, int remainArrow, int[] apeach, int[] lion) {
		// 배열이 완성되면
		if (index == 11 || remainArrow == 0) {
			lion[10] += remainArrow;
			int diff = getDiff(apeach, lion);
			if (maxDiff < diff || (maxDiff == diff && canAnswerChange(lion))) {
				maxDiff = diff;
				changeAnswer(lion);
			}
			lion[10] -= remainArrow;
			return;
		}

		int requiredArrow = apeach[index] + 1;

		if (remainArrow >= requiredArrow) {
			lion[index] = requiredArrow;
			// 선택하는 경우
			recur(index + 1, remainArrow - requiredArrow, apeach, lion);
			lion[index] = 0;
		}
		// 선택 안하는 경우
		recur(index + 1, remainArrow, apeach, lion);
	}

	int getDiff(int[] apeach, int[] lion) {
		int apeachSum = 0;
		int lionSum = 0;
		for (int i = 0; i <= 10; i++) {
			if (lion[i] > apeach[i]) lionSum += 10 - i;
			else if (lion[i] < apeach[i]) apeachSum += 10 - i;
		}
		return lionSum - apeachSum;
	}

	void changeAnswer(int[] lion) {
		for (int i = 0; i <= 10; i++) {
			answer[i] = lion[i];
		}
	}

	boolean canAnswerChange(int[] lion) {
		for (int i = 10; i >= 0; i--) {
			if (answer[i] > lion[i]) return false;
			else if (lion[i] > answer[i]) return true;
		}
		return false;
	}
}
