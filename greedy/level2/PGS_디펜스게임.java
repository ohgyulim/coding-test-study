package greedy.level2;

import java.util.*;

public class PGS_디펜스게임 {
    public int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < enemy.length; i++) {
            if (pq.size() != k) {
                pq.offer(enemy[i]);
                continue;
            }

            if (pq.peek() < enemy[i] && pq.peek() <= n) {
                n -= pq.poll();
                pq.offer(enemy[i]);
            } else if (pq.peek() < enemy[i]) {
                return i;
            } else if (pq.peek() >= enemy[i] && enemy[i] <= n) {
                n -= enemy[i];
            } else if (pq.peek() >= enemy[i]) {
                return i;
            }
        }

        return answer;
    }
}
