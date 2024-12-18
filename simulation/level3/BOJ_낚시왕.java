package simulation.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shark {
	int y;
	int x;
	int speed;
	int dir;
	int size;

	Shark(int y, int x, int speed, int dir, int size) {
		this.y = y;
		this.x = x;
		this.speed = speed;
		this.dir = dir;
		this.size = size;
	}
}

public class BOJ_낚시왕 {
	static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static int R, C, M;
	static int answer;
	static Shark[][] matrix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new Shark[R + 1][C + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			matrix[r][c] = new Shark(r, c, s, d, z);
		}

		for (int i = 1; i <= C; i++) {
			fishingShark(i);
			moveShark();
		}
		System.out.println(answer);
	}

	public static void fishingShark(int x) {
		for (int i = 1; i <= R; i++) {
			if (matrix[i][x] != null) {
				answer += matrix[i][x].size;
				matrix[i][x] = null;
				break;
			}
		}
	}

	public static void moveShark() {
		Shark[][] newMatrix = new Shark[R + 1][C + 1];

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (matrix[i][j] == null) continue;

				Shark shark = matrix[i][j];
				moveSharkPosition(shark);

				if (newMatrix[shark.y][shark.x] == null || newMatrix[shark.y][shark.x].size < shark.size) {
					newMatrix[shark.y][shark.x] = shark;
				}
			}
		}
		matrix = newMatrix;
	}

	public static void moveSharkPosition(Shark shark) {
		int speed = shark.speed;
		int totalSteps;

		if (shark.dir == 1 || shark.dir == 2) { // 상하 이동
			totalSteps = (R - 1) * 2;
		} else { // 좌우 이동
			totalSteps = (C - 1) * 2;
		}

		speed %= totalSteps;

		while (speed > 0) {
			int ny = shark.y + dir[shark.dir - 1][0] * speed;
			int nx = shark.x + dir[shark.dir - 1][1] * speed;

			if (ny <= 0 || ny > R || nx <= 0 || nx > C) { // 벽에 부딪히는 경우
				if (shark.dir == 1 || shark.dir == 2) { // 위 또는 아래 방향
					if (ny <= 0) {
						speed -= shark.y - 1;
						shark.y = 1;
					} else if (ny > R) {
						speed -= R - shark.y;
						shark.y = R;
					}
					shark.dir = shark.dir == 1 ? 2 : 1;
				} else { // 오른쪽 또는 왼쪽 방향
					if (nx <= 0) {
						speed -= shark.x - 1;
						shark.x = 1;
					} else if (nx > C) {
						speed -= C - shark.x;
						shark.x = C;
					}
					shark.dir = shark.dir == 3 ? 4 : 3;
				}
			} else { // 벽에 부딪히지 않는 경우
				shark.y = ny;
				shark.x = nx;
				break;
			}
		}
	}
}