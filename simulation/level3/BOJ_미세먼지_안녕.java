package simulation.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_미세먼지_안녕 {
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static int[][] matrix;
	static int R, C;
	static int topIndex, bottomIndex;

	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		topIndex = 0;
		bottomIndex = 0;
		matrix = new int[R + 1][C + 1];

		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= C; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				if (matrix[i][j] == -1 && topIndex == 0)
					topIndex = i;
				else if (matrix[i][j] == -1 && topIndex != 0)
					bottomIndex = i;
			}
		}

		for (int i = 0; i < T; i++) {
			diffusion();
			moveTop();
			moveBottom();
		}
		System.out.println(getResult() + 2);

	}

	public static void diffusion() {
		int[][] diffusionMatrix = new int[R + 1][C + 1];
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (matrix[i][j] <= 0)
					continue;
				int count = getDiffusionCount(i, j);
				int diffusions = matrix[i][j] / 5;
				int remains = matrix[i][j] - (diffusions * count);
				for (int k = 0; k < 4; k++) {
					int nextY = i + dy[k];
					int nextX = j + dx[k];
					if (isInRange(nextY, nextX) && matrix[nextY][nextX] != -1) {
						diffusionMatrix[nextY][nextX] += diffusions;
					}
				}
				diffusionMatrix[i][j] += remains;
			}
		}
		matrix = diffusionMatrix;
		matrix[topIndex][1] = -1;
		matrix[bottomIndex][1] = -1;
	}

	public static int getDiffusionCount(int y, int x) {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			if (isInRange(nextY, nextX) && matrix[nextY][nextX] != -1)
				count += 1;
		}

		return count;
	}

	public static void moveTop() {
		Deque<Integer> queue = new LinkedList<>();
		if (topIndex == 1)
			return;
		for (int i = topIndex - 1; i >= 1; i--) {
			queue.offer(matrix[i][1]);
		}
		for (int i = 2; i <= C; i++) {
			queue.offer(matrix[1][i]);
		}
		for (int i = 2; i <= topIndex; i++) {
			queue.offer(matrix[i][C]);
		}
		for (int i = C - 1; i > 1; i--) {
			queue.offer(matrix[topIndex][i]);
		}
		queue.poll();
		queue.addLast(0);
		setTop(queue);
	}

	public static void setTop(Deque<Integer> queue) {
		for (int i = topIndex - 1; i >= 1; i--) {
			matrix[i][1] = queue.poll();
		}
		for (int i = 2; i <= C; i++) {
			matrix[1][i] = queue.poll();
		}
		for (int i = 2; i <= topIndex; i++) {
			matrix[i][C] = queue.poll();
		}
		for (int i = C - 1; i > 1; i--) {
			matrix[topIndex][i] = queue.poll();
		}
	}

	public static void moveBottom() {
		Deque<Integer> queue = new LinkedList<>();
		if (bottomIndex == R)
			return;
		for (int i = bottomIndex + 1; i <= R; i++) {
			queue.offer(matrix[i][1]);
		}
		for (int i = 2; i <= C; i++) {
			queue.offer(matrix[R][i]);
		}
		for (int i = R - 1; i >= bottomIndex; i--) {
			queue.offer(matrix[i][C]);
		}
		for (int i = C - 1; i > 1; i--) {
			queue.offer(matrix[bottomIndex][i]);
		}
		queue.poll();
		queue.addLast(0);
		setBottom(queue);
	}

	public static void setBottom(Deque<Integer> queue) {
		for (int i = bottomIndex + 1; i <= R; i++) {
			matrix[i][1] = queue.poll();
		}
		for (int i = 2; i <= C; i++) {
			matrix[R][i] = queue.poll();
		}
		for (int i = R - 1; i >= bottomIndex; i--) {
			matrix[i][C] = queue.poll();
		}
		for (int i = C - 1; i > 1; i--) {
			matrix[bottomIndex][i] = queue.poll();
		}
	}

	public static boolean isInRange(int nextY, int nextX) {
		return (nextY >= 1 && nextY <= R) && (nextX >= 1 && nextX <= C);
	}

	public static int getResult() {
		int result = 0;
		for (int i = 1; i <= R; i++) {
			result += Arrays.stream(matrix[i]).sum();
		}
		return result;
	}
}

