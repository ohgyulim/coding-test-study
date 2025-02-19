package brute_force.level3;

import java.util.*;

public class PGS_카드_짝_맞추기 {
	class Position {
		int y;
		int x;
		Position(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	ArrayList<int[]> events = new ArrayList<>();
	List<Position>[] cards = new ArrayList[7];
	public int solution(int[][] board, int r, int c) {
		int answer = 0;
		int n = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 0) continue;
				int number = board[i][j];
				n = Math.max(n, number);
				if (cards[number] == null) cards[number] = new ArrayList<>();
				cards[number].add(new Position(i, j));
			}
		}
		recur(1, n, new int[n + 1], new boolean[n + 1]);
		// events 순서대로 진행한다
		// 단, events 순서가 1, 2, 3 이라면 1-1 -> 1-2 / 1-2 -> 1-1 경우도 고려하여 계산한다
		return answer;
	}

	public int getChoiceCnt(int[][] board, int curY, int curX, int targetY, int targetX) {
		// 방향 바꿔서 이동 시 + 1, 카드 고를 시 + 1, 내 앞에 고를 카드 아닌 다른 카드 있으면 + 1
		int cnt = 0;
		if (curY != targetY) cnt += 1;
		if (curX != targetX) cnt += 1;

		int cardNum = board[targetY][targetX];
		while (curY != targetY) {
			if (curY > targetY) {
				curY -= 1;
			} else if (curY < targetY) {
				curY += 1;
			}
			// 다른 숫자 카드를 만났는데, 내가 원하는 카드가 아니라면
			if (board[curY][curX] != 0 && board[curY][curX] != cardNum) cnt += 1;
		}

		while (curX != targetX) {
			if (curX > targetX) {
				curX -= 1;
			} else if (curX < targetX) {
				curX += 1;
			}
			// 다른 숫자 카드를 만났는데, 내가 원하는 카드가 아니라면
			if (board[curY][curX] != 0 && board[curY][curX] != cardNum) cnt += 1;
		}

		// 원하는 곳에 도달했으면 선택한다.
		if (curY == targetY && curX == targetX) cnt += 1;

		return cnt;
	}

	public void recur(int index, int n, int[] arr, boolean[] check) {
		if (index > n) {
			events.add(arr.clone());
			return;
		}

		for (int i = 1; i <= n; i++) {
			if (arr[index] == 0 && !check[i]) {
				arr[index] = i;
				check[i] = true;
				recur(index + 1, n, arr, check);
				arr[index] = 0;
				check[i] = false;
			}
		}
	}
}
