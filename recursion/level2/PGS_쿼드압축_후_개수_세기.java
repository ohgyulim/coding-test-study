package recursion.level2;

import java.util.*;

public class PGS_쿼드압축_후_개수_세기 {
	class Region {
		int y, x, size;
		public Region(int y, int x, int size) {
			this.y = y;
			this.x = x;
			this.size = size;
		}
	}

	public int[] solution(int[][] arr) {
		int n = arr.length;
		int[] answer = new int[2];

		Stack<Region> stack = new Stack<>();
		stack.push(new Region(0, 0, n));

		while (!stack.isEmpty()) {
			Region cur = stack.pop();
			int y = cur.y;
			int x = cur.x;
			int size = cur.size;

			boolean isCompressed = true;
			int first = arr[y][x];

			outer:
			for (int i = y; i < y + size; i++) {
				for (int j = x; j < x + size; j++) {
					if (arr[i][j] != first) {
						isCompressed = false;
						break outer;
					}
				}
			}

			if (isCompressed) {
				answer[first] += 1;
			} else {
				int half = size / 2;
				stack.push(new Region(y, x, half)); // 좌상단
				stack.push(new Region(y, x + half, half)); // 우상단
				stack.push(new Region(y + half, x, half)); // 좌하단
				stack.push(new Region(y + half, x + half, half)); // 우하단
			}
		}

		return answer;
	}
}
