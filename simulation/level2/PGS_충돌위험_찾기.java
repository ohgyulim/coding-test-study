package simulation.level2;

import java.util.*;

class Position {
	int y;
	int x;
	Position(int y, int x) {
		this.y = y;
		this.x = x;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return y == position.y && x == position.x;
	}

	@Override
	public int hashCode() {
		return Objects.hash(y, x);
	}
}

class PGS_충돌위험_찾기 {
	Map<Integer, List<Position>> hashMap;
	int[] dy = {1, -1, 0, 0};
	int[] dx = {0, 0, 1, -1};
	public int solution(int[][] points, int[][] routes) {
		hashMap = new HashMap<>();
		int answer = 0;
		for (int i = 0; i < routes.length; i++) {
			int step = 0;
			for (int j = 0; j < routes[i].length - 1; j++) {
				int[] startPos = points[routes[i][j] - 1];
				int[] endPos = points[routes[i][j + 1] - 1];
				step = moveStartToEnd(startPos[0], startPos[1], endPos[0], endPos[1], step);
			}
		}
		for (var entrySet : hashMap.entrySet()) {
			List<Position> positions = entrySet.getValue();
			Map<Position, Integer> positionCountMap = new HashMap<>();

			for (Position pos : positions) {
				positionCountMap.put(pos, positionCountMap.getOrDefault(pos, 0) + 1);
			}

			for (var entry : positionCountMap.entrySet()) {
				Position pos = entry.getKey();
				int count = entry.getValue();

				if (count > 1) {
					answer++;  // 중복이 1회 이상일 때 answer 증가
				}
			}
		}
		return answer;
	}

	public int moveStartToEnd(int y, int x, int endY, int endX, int step) {
		int[][] isVisit = new int[101][101];
		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(y, x));
		isVisit[y][x] = 1;

		while(!queue.isEmpty()) {
			Position pos = queue.poll();
			int curY = pos.y;
			int curX = pos.x;

			if (curY == endY && curX == endX) break;

			for (int i = 2; i < 4; i++) {
				int nextY = curY;
				int nextX = curX;
				if (nextY < endY) nextY += dy[0];
				else if (nextY > endY) nextY += dy[1];
				else nextX += dx[i];


				if (!(nextY < 1 || nextY > 100 || nextX < 1 || nextX > 100) && isVisit[nextY][nextX] == 0)
				{
					isVisit[nextY][nextX] = isVisit[curY][curX] + 1;
					queue.offer(new Position(nextY, nextX));
				}
			}
		}
		return findBestPath(y, x, endY, endX, isVisit, step);
	}

	public int findBestPath(int y, int x, int endY, int endX, int[][] isVisit, int step) {
		List<Position> path = new ArrayList<>();
		int curY = endY;
		int curX = endX;
		path.add(new Position(curY, curX));

		while (curY != y || curX != x) {
			for (int i = 0; i < 4; i++) {
				int prevY = curY - dy[i];
				int prevX = curX - dx[i];

				if (prevY >= 1 && prevY <= 100 && prevX >= 1 && prevX <= 100 &&
					isVisit[prevY][prevX] == (isVisit[curY][curX] - 1)) {
					path.add(new Position(prevY, prevX));
					curY = prevY;
					curX = prevX;
					break;
				}
			}
		}

		Collections.reverse(path);
		if (step != 0) path.remove(0);
		for (int i = 0; i < path.size(); i++) {
			if (!hashMap.containsKey(i + 1 + step)) hashMap.put((i + 1 + step), new ArrayList<>());
			hashMap.get(i + 1 + step).add(path.get(i));
		}
		return path.size() + step;
	}
}