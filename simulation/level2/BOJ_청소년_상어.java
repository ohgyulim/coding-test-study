package simulation.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_청소년_상어 {
	static class Fish {
		int y;
		int x;
		int number;
		int dir;
		Fish(int y, int x, int number, int dir) {
			this.y = y;
			this.x = x;
			this.number = number;
			this.dir = dir;
		}

		void changePos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int answer = 0;
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Fish[][] fishMap = new Fish[4][4];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				int number = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				fishMap[i][j] = new Fish(i, j, number, dir - 1);
			}
		}
		recur(fishMap, 0, 0, 0);
		System.out.println(answer);
	}

	public static void recur(Fish[][] fishMap, int y, int x, int sum) {
		if (y < 0 || y >= 4 || x < 0 || x >= 4 || fishMap[y][x] == null) {
			answer = Math.max(sum, answer);
			return;
		}

		Fish[][] newFishMap = getCopyFishMap(fishMap);
		Fish fish = newFishMap[y][x]; // 먹은 물고기
		newFishMap[y][x] = null;
		sum += fish.number;

		moveFish(newFishMap, y, x);

		for (int i = 1; i <= 3; i++) {
			int nextY = y + i * dy[fish.dir];
			int nextX = x + i * dx[fish.dir];
			if (nextY >= 0 && nextY < 4 && nextX >= 0 && nextX < 4 && newFishMap[nextY][nextX] != null) {
				recur(newFishMap, nextY, nextX, sum);
			}
		}

		answer = Math.max(sum, answer);
	}

	public static Fish[][] getCopyFishMap(Fish[][] fishMap) {
		Fish[][] newFishMap = new Fish[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (fishMap[i][j] != null) {
					Fish originalFish = fishMap[i][j];
					newFishMap[i][j] = new Fish(originalFish.y, originalFish.x, originalFish.number, originalFish.dir);
				}
			}
		}
		return newFishMap;
	}

	public static void moveFish(Fish[][] fishMap, int sharkY, int sharkX) {
		for (int fishNumber = 1; fishNumber <= 16; fishNumber++) {
			Fish fish = null;
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					if (fishMap[y][x] != null && fishMap[y][x].number == fishNumber) {
						fish = fishMap[y][x];
						break;
					}
				}
				if (fish != null) break;
			}

			if (fish == null) continue;

			int curY = fish.y;
			int curX = fish.x;
			int dir = fish.dir;

			for (int j = 0; j < 8; j++) {
				int nextY = curY + dy[dir];
				int nextX = curX + dx[dir];
				if (canMove(nextY, nextX, sharkY, sharkX)) {
					fish.dir = dir;
					swapFish(fishMap, curY, curX, nextY, nextX);
					break;
				}
				dir = (dir + 1) % 8;
			}
		}
	}

	public static boolean canMove(int y, int x, int sharkY, int sharkX) {
		return y >= 0 && y < 4 && x >= 0 && x < 4 && !(y == sharkY && x == sharkX);
	}

	public static void swapFish(Fish[][] fishMap, int fromY, int fromX, int toY, int toX) {
		Fish fromFish = fishMap[fromY][fromX];
		Fish toFish = fishMap[toY][toX];
		fromFish.changePos(toY, toX);
		if (toFish == null) {
			fishMap[toY][toX] = fromFish;
			fishMap[fromY][fromX] = null;
		} else {
			toFish.changePos(fromY, fromX);
			fishMap[fromY][fromX] = toFish;
			fishMap[toY][toX] = fromFish;
		}
	}
}