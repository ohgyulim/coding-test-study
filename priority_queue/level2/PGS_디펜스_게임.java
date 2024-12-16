package priority_queue.level2;

import java.util.*;

public class PGS_디펜스_게임 {
	public int solution(int n, int k, int[] enemy) {
		int answer = 0;
		PriorityQueue<Integer> enemies = new PriorityQueue<>(Collections.reverseOrder());

		for (int enemyCount : enemy) {
			enemies.add(enemyCount);
			if (n >= enemyCount) {
				n -= enemyCount;
			} else {
				if (k <= 0)
					break;
				int maxEnemyCount = enemies.poll();
				n += (maxEnemyCount - enemyCount);
				k -= 1;
			}
			answer += 1;
		}


		return answer;
	}
}
