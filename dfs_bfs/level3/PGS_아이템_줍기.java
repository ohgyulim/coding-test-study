package dfs_bfs.level3;

import java.util.*;

public class PGS_아이템_줍기 {
	// 1. 사각형을 하나씩 순회하면서 생성되는 모든 꼭짓점의 정보를 저장한다
	// 2. 출발 지점에서 출발하여 x축 or y축 동일 선상에 있는 꼭짓점을 선택하여 이동한다.
	//    2-1. 이때, 꼭짓점으로 이동할 수 있는지 여부를 판단한다. (사각형 건너편에 있는 경우 이동 불가)
	class Position {
		int y;
		int x;
		Position(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	List<Position> positions = new ArrayList<>();
	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		int answer = 0;
		addPositions(rectangle[0]);

		for (int i = 1; i < rectangle.length; i++) {

		}

		return answer;
	}

	// 기존 사각형들과 새로운 사각형이 겹친다면, 겹치는 꼭짓점은 삭제 + 사각형 꼭짓점이 아닌 두 사각형이 만나는 지점을 추가
	public void addPositions(int[] pos) {
		int x1 = pos[0];
		int y1 = pos[1];
		int x2 = pos[2];
		int y2 = pos[3];

		positions.add(new Position(y1, x1));
		positions.add(new Position(y2, x1));
		positions.add(new Position(y1, x2));
		positions.add(new Position(y2, x2));
	}
}
