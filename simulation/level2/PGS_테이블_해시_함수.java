package simulation.level2;

import java.util.Arrays;

public class PGS_테이블_해시_함수 {
	public int solution(int[][] data, int col, int row_begin, int row_end) {
		int answer = 0;
		Arrays.sort(data, (o1, o2) -> {
			if (o1[col - 1] == o2[col - 1]) {
				return o2[0] - o1[0];
			}
			return o1[col - 1] - o2[col - 1];
		});

		for (int i = row_begin; i <= row_end; i++) {
			int value = 0;
			for (int elem : data[i - 1]) {
				value += elem % i;
			}
			answer ^= value;
		}

		return answer;
	}
}
