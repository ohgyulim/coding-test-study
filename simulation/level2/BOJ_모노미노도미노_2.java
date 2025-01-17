package simulation.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_모노미노도미노_2 {
	static boolean[][] blueBlocks = new boolean[4][6];
	static boolean[][] greenBlocks = new boolean[6][4];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());


		}
	}

	static void setBlocksInBlue(int type, int y, int x) {
		switch (type) {
			case 1: {
				put1x1BlockAtBlue(y, x); break;
			}
			case 2: {
				put1x2BlockAtBlue(x, x + 1, y); break;
			}
			case 3: {
				put2x1BlockAtBlue(y, y + 1, x); break;
			}
		}
	}

	static void put1x1BlockAtBlue(int y, int x) {
		if (!blueBlocks[y][x] || x == 0) {
			blueBlocks[y][x] = true;
			return;
		}

		put1x1BlockAtBlue(y, x - 1);
	}

	static void put1x2BlockAtBlue(int x1, int x2, int y) {
		if ((!blueBlocks[y][x1] && !blueBlocks[y][x2]) || x1 == 0) {
			blueBlocks[y][x1] = true;
			blueBlocks[y][x2] = true;
			return;
		}

		put1x2BlockAtBlue(x1 - 1, x2 - 1, y);
	}

	static void put2x1BlockAtBlue(int y1, int y2, int x) {
		if ((!blueBlocks[y1][x] && !blueBlocks[y2][x]) || x == 0) {
			blueBlocks[y1][x] = true;
			blueBlocks[y2][x] = true;
			return;
		}

		put2x1BlockAtBlue(y1, y2, x - 1);
	}


	static int calcBlue() {
		int score = 0;
		boolean[] canGetScore = new boolean[6];

		// 점수를 얻은 열을 체크하고 제거
		for (int x = 5; x >= 0; x--) {
			if (blueBlocks[0][x] && blueBlocks[1][x] && blueBlocks[2][x] && blueBlocks[3][x]) {
				blueBlocks[0][x] = false;
				blueBlocks[1][x] = false;
				blueBlocks[2][x] = false;
				blueBlocks[3][x] = false;
				canGetScore[x] = true;
				score += 1;
			}
		}

		if (score > 0) {
			// 블럭을 오른쪽으로 이동
			for (int x = 5; x > 0; x--) { // 뒤에서 앞으로 탐색
				if (canGetScore[x]) { // 점수를 얻은 열은 빈 공간이므로 건너뜀
					continue;
				}
				for (int y = 0; y <= 3; y++) {
					if (blueBlocks[y][x - 1]) { // 이전 열에 블럭이 있다면

					}
				}
			}
		}

		return score;
	}


	static void setBlocksInGreen(int type) {

	}
}
