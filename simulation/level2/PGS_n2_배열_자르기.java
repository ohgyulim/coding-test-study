package simulation.level2;

public class PGS_n2_배열_자르기 {
	public int[] solution(int n, long left, long right) {
		int size = (int)(right - left + 1);
		int[] answer = new int[size];
		int index = 0;
		for (long i = left; i <= right; i++) {
			int y = (int)(i / n);
			int x = (int)(i % n);
			if (y <= x) answer[index++] = x + 1;
			else answer[index++] = y + 1;
		}
		return answer;
	}
}
