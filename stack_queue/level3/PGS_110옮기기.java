package stack_queue.level3;

import java.util.*;

class PGS_110옮기기 {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        int idx = 0;
        for (String x : s) {
            answer[idx++] = simulation(x);
        }

        return answer;
    }

    private String simulation(String x) {
        if (x.length() <= 3) {
            return x;
        }
        char[] xArray = x.toCharArray();
        Deque<Character> queue = new LinkedList<>();
        int cnt110 = 0;
        for (char c : xArray) {
            queue.offer(c);
            if (queue.size() >= 3) {
                char first = queue.pollLast();
                if (first != '0') {
                    queue.offer(first);
                    continue;
                }

                char second = queue.pollLast();
                if (second != '1') {
                    queue.offer(second);
                    queue.offer(first);
                    continue;
                }

                char third = queue.pollLast();
                if (third != '1') {
                    queue.offer(third);
                    queue.offer(second);
                    queue.offer(first);
                    continue;
                }

                cnt110++;
            }
        }

        char[] result = new char[x.length()];
        int idx = x.length() - 1;
        while (!queue.isEmpty()) {
            char c = queue.pollLast();
            if (c == '0' && cnt110 > 0) {
                for (int i = 0; i < cnt110; i++) {
                    result[idx--] = '0';
                    result[idx--] = '1';
                    result[idx--] = '1';
                }
                cnt110 = 0;
            }
            result[idx--] = c;
        }

        if (cnt110 > 0) {
            for (int i = 0; i < cnt110; i++) {
                result[idx--] = '0';
                result[idx--] = '1';
                result[idx--] = '1';
            }
        }

        return new String(result);
    }
}