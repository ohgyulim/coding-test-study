package dp.level3;


import java.util.*;

// 가중치가 같은 경우, 왼손/오른손 결정하는 로직 없어서 실패 -> 모든 경우의 수 구하기 위해 재귀 써야될 것 같긴함..
// dp로 풀 수 있을 것 같은데... 모르겠다..
class Position {
	int y;
	int x;
	Position(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
class PGS_숫자_타자_대회 {
	int[] dy = {-1, 0, 1, 0}; // 상하좌우
	int[] dx = {0, 1, 0, -1};
	int[] ddy = {-1, 1, 1, -1}; // 북동, 남동, 남서, 북서
	int[] ddx = {1, 1, -1, -1};
	Integer[][] matrix = new Integer[5][4];
	public int solution(String numbers) {
		int answer = 0;
		Position leftHandPos = new Position(2, 1);
		Position rightHandPos = new Position(2, 3);
		Map<Integer, Position> posMap = new HashMap<>();
		matrix[4][2] = 0;
		posMap.put(0, new Position(4, 2));
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				matrix[i][j] = 3 * i - (3 - j);
				posMap.put(3 * i - (3 - j), new Position(i, j));
			}
		}

		for (int i = 0; i < numbers.length(); i++) {
			int goalNumber = numbers.charAt(i) - '0';
			Position goal = posMap.get(goalNumber);
			int leftWeight = bfs(leftHandPos, goal, rightHandPos);
			int rightWeight = bfs(rightHandPos, goal, leftHandPos);
			if (leftWeight >= rightWeight) {
				rightHandPos = goal;
				answer += rightWeight;
			} else {
				leftHandPos = goal;
				answer += leftWeight;
			}
		}

		return answer;
	}

	public int bfs(Position pos, Position goal, Position otherHandPos) {
		int[][] visited = new int[5][4];
		visited[pos.y][pos.x] = 1;
		Queue<Position> queue = new LinkedList<>();
		queue.offer(pos);

		while (!queue.isEmpty()) {
			Position position = queue.poll();
			int curY = position.y;
			int curX = position.x;
			if (curY == goal.y && curX == goal.x) break;

			// 상하좌우
			for (int i = 0; i < 4; i++) {
				int nextY = curY + dy[i];
				int nextX = curX + dx[i];
				if (isInRange(nextY, nextX) && visited[nextY][nextX] == 0
					&& !(nextY == otherHandPos.y && nextX == otherHandPos.x)) {
					queue.offer(new Position(nextY, nextX));
					visited[nextY][nextX] = visited[curY][curX] + 2;
				}
			}

			// 대각선
			for (int i = 0; i < 4; i++) {
				int nextY = curY + ddy[i];
				int nextX = curX + ddx[i];
				if (isInRange(nextY, nextX) && visited[nextY][nextX] == 0
					&& !(nextY == otherHandPos.y && nextX == otherHandPos.x)) {
					queue.offer(new Position(nextY, nextX));
					visited[nextY][nextX] = visited[curY][curX] + 3;
				}
			}
		}

		return visited[goal.y][goal.x] - 1;
	}

	public boolean isInRange(int y, int x) {
		if (y <= 0 || y >= 5 || x <= 0 || x >= 4) return false;
		if (matrix[y][x] == null) return false;
		return true;
	}
}
