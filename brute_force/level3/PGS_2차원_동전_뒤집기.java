package brute_force.level3;

// 완전 탐색
public class PGS_2차원_동전_뒤집기 {
	public int solution(int[][] beginning, int[][] target) {
		int ySize = beginning.length;
		int xSize = beginning[0].length;
		int MAX = ySize * xSize + 1;
		int answer = MAX;

		for (int y = 0; y < (1 << ySize); y++) {
			for (int x = 0; x < (1 << xSize); x++) {
				int cnt = Integer.bitCount(y) + Integer.bitCount(x);
				if (cnt < answer && compare(beginning, target, y, x, ySize, xSize)) {
					answer = cnt;
				}
			}
		}

		return answer < MAX ? answer : -1;
	}

	private boolean compare(int[][] beginning, int[][] target, int y, int x, int ySize, int xSize) {
		for (int i = 0; i < ySize; i++) {
			for (int j = 0; j < xSize; j++) {
				// y의 i번째 비트 -> 1인지 확인
				// x의 j번째 비트 -> 1인지 확인
				int diff = ((y >> i) & 1) + ((x >> j) & 1);
				// 두 비트가 같으면 %2 == 0
				if ((beginning[i][j] + diff) % 2 != target[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
