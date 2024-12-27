package simulation.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_원판_돌리기 {
	static int N, M;
	static List<Integer>[] matrix;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		matrix = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			matrix[i] = new ArrayList<>();
			for (int j = 0; j < M; j++) {
				matrix[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()); // 몇 번째 칸 (x 배수)
			int d = Integer.parseInt(st.nextToken()); // 0 = 시계 / 1 = 반시계
			int k = Integer.parseInt(st.nextToken()); // 몇 칸 이동
			if (d == 0) rightShift(x, k);
			else leftShift(x, k);
			boolean isRemoved = removeNearElem();
			if (!isRemoved) {
				changeElem();
			}
		}

		System.out.println(getSumAndCount()[0]);
	}

	// 시계방향
	public static void rightShift(int x, int k) {
		int start = x - 1;
		for (int i = start; i < N; i += x) {
			List<Integer> list = matrix[i];
			for (int j = 0; j < k; j++) {
				list.add(0, list.remove(M - 1));
			}
		}
	}

	// 반시계 방향
	public static void leftShift(int x, int k) {
		int start = x - 1;
		for (int i = start; i < N; i += x) {
			List<Integer> list = matrix[i];
			for (int j = 0; j < k; j++) {
				list.add(list.remove(0));
			}
		}
	}

	public static boolean removeNearElem() {
		boolean flag = false;
		boolean[][] toRemove = new boolean[N][M];

		// 인접 요소 확인
		for (int i = 0; i < N; i++) {
			int size = matrix[i].size();
			for (int j = 0; j < size; j++) {
				int current = matrix[i].get(j);
				if (current == 0) continue;

				if (current == matrix[i].get((j + 1) % size)) {
					toRemove[i][j] = true;
					toRemove[i][(j + 1) % size] = true;
				}

				// 위쪽 확인
				if (i > 0 && current == matrix[i-1].get(j)) {
					toRemove[i][j] = true;
					toRemove[i - 1][j] = true;
				}

				// 아래쪽 확인
				if (i < N - 1 && current == matrix[i+1].get(j)) {
					toRemove[i][j] = true;
					toRemove[i + 1][j] = true;
				}
			}
		}

		// 제거
		for (int i = 0; i < N; i++) {
			List<Integer> list = matrix[i];
			for (int j = 0; j < M; j++) {
				if (toRemove[i][j]) {
					flag = true;
					list.set(j, 0);
				}
			}
		}

		return flag;
	}

	public static int[] getSumAndCount() {
		int sum = 0;
		int count = 0;
		for (int i = 0; i < N; i++) {
			List<Integer> list = matrix[i];
			for (int j = 0; j < M; j++) {
				sum += list.get(j);
				if (list.get(j) != 0) count++;
			}
		}
		return new int[]{sum, count};
	}

	public static void changeElem() {
		int[] getSumAndCount = getSumAndCount();
		int sum = getSumAndCount[0];
		int count = getSumAndCount[1];
		double avg = (double)sum / count;

		for (int i = 0; i < N; i++) {
			List<Integer> list = matrix[i];
			for (int j = 0; j < M; j++) {
				int current = list.get(j);
				if (current == 0) continue;
				if (current < avg) list.set(j, current + 1);
				else if (current > avg) list.set(j, current - 1);
			}
		}
	}
}
