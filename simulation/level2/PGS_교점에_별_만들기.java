package simulation.level2;

import java.util.*;

public class PGS_교점에_별_만들기 {
	class Position {
		int y;
		int x;
		Position(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	public String[] solution(int[][] line) {
		int n = line.length;
		List<Position> positions = new ArrayList<>();

		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		for (int i = 0; i < n - 1; i++) {
			long A = line[i][0];
			long B = line[i][1];
			long E = line[i][2];
			for (int j = i + 1; j < n; j++) {
				long C = line[j][0];
				long D = line[j][1];
				long F = line[j][2];

				long bottom = (long)(A * D) - (long)(B * C);
				if (bottom == 0L) continue;

				long xTop = (long)(B * F) - (long)(E * D);
				long yTop = (long)(E * C) - (long)(A * F);
				if (xTop % bottom != 0 || yTop % bottom != 0) continue;

				int x = (int)(xTop / bottom);
				int y = (int)(yTop / bottom);

				minX = Math.min(minX, x);
				maxX = Math.max(maxX, x);
				minY = Math.min(minY, y);
				maxY = Math.max(maxY, y);

				positions.add(new Position(y, x));
			}
		}

		int ansY = maxY - minY + 1;
		int ansX = maxX - minX + 1;
		String[] answer = new String[ansY];
		for (int i = 0; i < ansY; i++) {
			char[] row = new char[ansX];
			Arrays.fill(row, '.');
			answer[i] = new String(row);
		}

		StringBuilder sb = new StringBuilder();
		for (Position pos : positions) {
			sb.setLength(0);
			int y = maxY - pos.y;
			int x = pos.x - minX;
			sb.append(answer[y]);
			sb.setCharAt(x, '*');
			answer[y] = sb.toString();
		}

		return answer;
	}
}
