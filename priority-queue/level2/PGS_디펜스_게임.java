package priority-queue.level2;

import java.util.*;

public class PGS_디펜스_게임 {

    // O(N), O(NlogN), O(logN)
    // 그리디.
    // 우선순위큐(enemy 내림차 정렬)
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;

        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int round;
        for (round=0;round<enemy.length;round++) {
            sum += enemy[round];
            pq.add(enemy[round]);

            if (sum <= n) {
                continue;
            }

            if (k == 0) {
                break;
            }

            int max = pq.poll();
            sum -= max;
            k--;
        }

        answer = round;

        return answer;
    }
}
