package prefix_sum.level3;

import java.util.*;

public class PGS_풍선_터트리기 {
	public int solution(int[] a) {
		int n = a.length;
		if (n == 1) return 1;
		else if (n == 2) return 2;

		int answer = n;
		int left = a[0];
		// 1. 오른쪽 배열 각 위치마다 가장 작은 값으로 초기화
		int[] rights = new int[n];
		Arrays.fill(rights, 1_000_000_001);
		rights[n - 1] = a[n - 1];
		for (int i = n - 2; i >= 2; i--) {
			rights[i] = Math.min(rights[i + 1], a[i]);
		}

		// 2. mid 값을 이동시키면서 left와 right값 비교
		for (int i = 1; i < n - 1; i++) {
			left = Math.min(left, a[i - 1]);
			int mid = a[i];
			int right = rights[i + 1];
			// 풍선을 터트리지 못하는 경우: mid가 가장 큰 경우
			if (mid > left && mid > right) answer -= 1;
		}

		return answer;
	}
}
