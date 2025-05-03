package brute_force.level2;

public class PGS_카펫 {
	public int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		int area = brown + yellow;
		int maxLen = area / 3;

		find:
		for (int x = 3; x <= maxLen; x++) {
			for (int y = 3; y <= x; y++) {
				if (y * x != area || !isAnswer(y, x, brown)) continue;
				answer[0] = x;
				answer[1] = y;
				break find;
			}
		}

		return answer;
	}

	public boolean isAnswer(int y, int x, int brown) {
		int line = (y * 2) + (x * 2) - 4;
		return line == brown;
	}
}
