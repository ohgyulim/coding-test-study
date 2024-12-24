package simulation.level2;

import java.util.*;

public class PGS_우박수열_정적분 {
	ArrayList<Double> areas = new ArrayList<>();

	public double[] solution(int k, int[][] ranges) {
		areas.add(0.0);

		int n = calcCollatz(k);

		double[] prefixSum = new double[areas.size()];
		for (int i = 1; i < areas.size(); i++) {
			prefixSum[i] = prefixSum[i - 1] + areas.get(i);
		}

		double[] answer = new double[ranges.length];
		int index = 0;
		for (int[] range : ranges) {
			int start = range[0];
			int end = n + range[1];

			if (start < 0 || end < 0 || start >= areas.size() || end >= areas.size() || start > end) {
				answer[index++] = -1.0;
			} else {
				answer[index++] = prefixSum[end] - prefixSum[start];
			}
		}

		return answer;
	}

	public int calcCollatz(int k) {
		if (k == 1) return 0;

		int y1 = k;
		int y2;
		double area;

		if (k % 2 == 0) {
			y2 = k / 2;
			area = y2 + (y1 - y2) * 0.5;
		} else {
			y2 = k * 3 + 1;
			area = y1 + (y2 - y1) * 0.5;
		}

		areas.add(area);
		return 1 + calcCollatz(y2);
	}
}
