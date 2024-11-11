package greedy.level2;

import java.util.*;

public class PGS_요격_시스템 {
	public int solution(int[][] targets) {
		int answer = 0;
		// end를 기준으로 오름차순 정렬
		Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);

		int curPos = 0;
		for (int[] target : targets) {
			int start = target[0];
			int end = target[1];
			if (curPos <= start) {
				curPos = end;
				answer += 1;
			}
		}
		return answer;
	}
}
