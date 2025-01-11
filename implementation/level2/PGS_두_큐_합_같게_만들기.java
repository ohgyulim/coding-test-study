package implementation.level2;

import java.util.*;

class PGS_두_큐_합_같게_만들기 {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;

        long queue1Sum = 0;
        long total = 0;

        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();


        for (int i=0;i<queue1.length;i++) {
            q1.addLast(queue1[i]);
            q2.addLast(queue2[i]);

            queue1Sum += queue1[i];
            total += (queue1[i] + queue2[i]);
        }


        if (total % 2 == 0) { // 짝수일 때만
            answer = 0;
            long target = total / 2;

            while (true) {

                if (answer > (queue1.length + queue2.length) * 2) {
                    answer = -1;
                    break;
                }

                if (queue1Sum == target) {
                    break;
                }

                if (queue1Sum > target) {
                    queue1Sum -= q1.peekFirst();
                    q2.addLast(q1.pollFirst());
                }
                else {
                    queue1Sum += q2.peekFirst();
                    q1.addLast(q2.pollFirst());
                }

                answer ++;
            }

        }

        return answer;
    }
}