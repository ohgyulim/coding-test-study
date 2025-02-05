package simulation.level2;

public class PGS_거리두기_확인하기 {
	int[] dy = {-1, 0, 1, 0, -2, 0, 2, 0, -1, 1, 1, -1};
	int[] dx = {0, 1, 0, -1, 0, 2, 0, -2, 1, 1, -1, -1};

	public int[] solution(String[][] places) {
		int[] answer = new int[5];
		int index = 0;
		for (String[] place : places) {
			boolean isOk = true;
			nextPlace: for (int i = 0; i < 5; i++) {
				String line = place[i];
				for (int j = 0; j < 5; j++) {
					if (line.charAt(j) == 'P') {
						isOk = checkDistance(place, i, j);
						if (!isOk) break nextPlace;
					}
				}
			}
			answer[index++] = isOk ? 1 : 0;
		}
		return answer;
	}

	public boolean checkDistance(String[] place, int y, int x) {
		for (int i = 0; i < 12; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			if (isInRange(nextY, nextX)) {
				char object = place[nextY].charAt(nextX);
				if (object == 'P') {
					if (i < 4) return false;
					else if (i >= 4 && i < 8) {
						char betweenObj = place[y + dy[i - 4]].charAt(x + dx[i - 4]);
						return betweenObj == 'X';
					}
					else {
						char diagonalObj1 = place[y + dy[(i - 7) % 4]].charAt(x + dx[(i - 7) % 4]);
						char diagonalObj2 = place[y + dy[i - 8]].charAt(x + dx[i - 8]);
						return diagonalObj1 == 'X' && diagonalObj2 == 'X';
					}
				}
			}
		}
		return true;
	}

	public boolean isInRange(int nextY, int nextX) {
		return nextY >= 0 && nextY < 5 && nextX >= 0 && nextX < 5;
	}
}
