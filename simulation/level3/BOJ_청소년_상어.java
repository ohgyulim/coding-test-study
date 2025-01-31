package simulation.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_청소년_상어 {
	static class Shark {
		int number;
		int y;
		int x;
		int dir;
		HashMap<Integer, int[]> dirs = new HashMap<>();
		Shark(int number, int y, int x) {
			this.number = number;
			this.y = y;
			this.x = x;
		}

		void setPos(int y, int x) {
			this.y = y;
			this.x = x;
		}

		void setDir(int dir) {
			this.dir = dir;
		}

		void addDir(int key, int[] dirs) {
			this.dirs.put(key, dirs);
		}
	}

	static class Node {
		int sharkNum;
		int count;
		Node(int sharkNum, int count) {
			this.sharkNum = sharkNum;
			this.count = count;
		}
	}

	static int N;
	static int k;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static Node[][] map;
	static List<Shark> sharks = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new Node[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int sharkNum = Integer.parseInt(st.nextToken());
				if (sharkNum == 0) map[i][j] = new Node(0, 0);
				else {
					map[i][j] = new Node(sharkNum, k);
					sharks.add(new Shark(sharkNum, i, j));
				}
			}
		}
		sharks.sort((o1, o2) -> o1.number - o2.number);

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int dir = Integer.parseInt(st.nextToken());
			sharks.get(i).setDir(dir - 1);
		}

		for (int i = 0; i < M; i++) {
			for (int dir = 1; dir <= 4; dir++) {
				st = new StringTokenizer(br.readLine(), " ");
				int[] dirs = new int[4];
				for (int j = 0; j < 4; j++) {
					dirs[j] = Integer.parseInt(st.nextToken()) - 1;
				}
				sharks.get(i).addDir(dir - 1, dirs);
			}
		}

		int time = 0;
		while (time <= 1000 && sharks.size() > 1) {
			moveSharks();
			minusCount();
			time += 1;
		}
		System.out.println(time > 1000 ? -1 : time);
	}

	public static void moveSharks() {
		Node[][] newMap = new Node[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = new Node(map[i][j].sharkNum, map[i][j].count);
			}
		}

		List<Shark> survivedSharks = new ArrayList<>();
		for (Shark shark : sharks) {
			int curY = shark.y;
			int curX = shark.x;
			int sharkNum = shark.number;
			int[] curDirs = shark.dirs.get(shark.dir);

			for (int d : curDirs) {
				int nextY = curY + dy[d];
				int nextX = curX + dx[d];

				if (!isInRange(nextY, nextX)) continue;
				if (map[nextY][nextX].sharkNum == 0) {
					shark.setDir(d);
					shark.setPos(nextY, nextX);

					if (newMap[nextY][nextX].sharkNum == 0 || newMap[nextY][nextX].sharkNum > sharkNum) {
						newMap[nextY][nextX].sharkNum = sharkNum;
						newMap[nextY][nextX].count = k;
					}
					break;
				} else if (map[nextY][nextX].sharkNum == sharkNum) {
					shark.setDir(d);
					shark.setPos(nextY, nextX);
					newMap[nextY][nextX].sharkNum = sharkNum;
					newMap[nextY][nextX].count = k;
					break;
				}
			}
		}

		HashSet<Integer> visited = new HashSet<>();
		for (Shark shark : sharks) {
			if (!visited.contains(shark.number)) {
				survivedSharks.add(shark);
				visited.add(shark.number);
			}
		}
		sharks = survivedSharks;
		map = newMap;
	}

	public static void minusCount() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].count > 0) {
					map[i][j].count -= 1;
					if (map[i][j].count == 0) {
						map[i][j].sharkNum = 0;
					}
				}
			}
		}
	}

	public static boolean isInRange(int y, int x) {
		return y >= 0 && y < N && x >= 0 && x < N;
	}
}
