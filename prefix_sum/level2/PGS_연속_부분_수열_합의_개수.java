package prefix_sum.level2;

import java.util.*;

// prefixSum 배열 안만들고 처리 가능할 듯
public class PGS_연속_부분_수열_합의_개수 {
	public int solution(int[] elements) {
		Set<Integer> set = new HashSet<>();

		int size = elements.length * 2;
		int[] prefixSum = new int[size];
		prefixSum[1] = elements[0];

		for (int i = 2; i < size; i++) {
			prefixSum[i] = prefixSum[i-1] + elements[(i-1) % elements.length];
			set.add(elements[(i-1) % elements.length]);
		}
		set.add(prefixSum[size - 1]);

		int start = 2;
		while (start < elements.length) {
			for (int s = start + elements.length; s >= start; s--) {
				set.add(prefixSum[s] - prefixSum[s - start]);
			}
			start += 1;
		}

		return set.size();
	}
}
