package greedy.level2;

import java.util.*;

class Mineral {
	int diamond;
	int iron;
	int stone;
	Mineral(int diamond, int iron, int stone) {
		this.diamond = diamond;
		this.iron = iron;
		this.stone = stone;
	}
	int useDiamond() {
		return diamond + iron + stone;
	}
	int useIron() {
		return diamond * 5 + iron + stone;
	}
	int useStone() {
		return diamond * 25 + iron * 5 + stone;
	}
}

public class PGS_광물_캐기 {
	public int solution(int[] picks, String[] minerals) {
		int answer = 0;
		PriorityQueue<Mineral> mineralList = new PriorityQueue<>(
			(m1, m2) -> m1.diamond == m2.diamond ? m2.iron - m1.iron : m2.diamond - m1.diamond
		);

		int size = 0;
		for (int pick : picks) {
			size += pick;
		}
		size = Math.min(size * 5, minerals.length);

		int diamondCount = 0;
		int ironCount = 0;
		int stoneCount = 0;
		for (int i = 0; i < size; i++) {
			switch (minerals[i]) {
				case "diamond":
					diamondCount++;
					break;
				case "iron":
					ironCount++;
					break;
				case "stone":
					stoneCount++;
					break;
			}
			if ((i + 1) % 5 == 0 || i == size - 1) {
				mineralList.add(new Mineral(diamondCount, ironCount, stoneCount));
				diamondCount = 0;
				ironCount = 0;
				stoneCount = 0;
			}
		}

		for (int i = 0; i < 3; i++) {
			if (picks[i] == 0) continue;
			int pickCount = picks[i];
			for (int pick = 1; pick <= pickCount; pick++) {
				if (mineralList.isEmpty()) break;
				Mineral mineral = mineralList.poll();
				if (i == 0) answer += mineral.useDiamond();
				else if (i == 1) answer += mineral.useIron();
				else answer += mineral.useStone();
			}
		}
		return answer;
	}
}
