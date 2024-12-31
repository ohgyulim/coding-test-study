package stack_queue.level2;

import java.util.*;

public class PGS_택배상자 {
    public int solution(int[] orders) {
        int answer = 0;

        Deque<Integer> stack = new LinkedList<>();
        int idx = 0;
        for (int i = 1; i <= orders.length; i++) {
            if (orders[idx] != i && (stack.isEmpty() || orders[idx] != stack.peek())) {
                stack.addFirst(i);
            } else if (orders[idx] != i && !stack.isEmpty() && orders[idx] == stack.peek()) {
                stack.poll();
                answer++;
                idx++;
                i--;
            } else {
                answer++;
                idx++;
            }
        }

        while (!stack.isEmpty() && stack.poll() == orders[idx++]) {
            answer++;
        }


        return answer;
    }
}
