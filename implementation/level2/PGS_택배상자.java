package implementation.level2;


import java.util.*;

class PGS_택배상자 {
    public int solution(int[] order) {
        int answer = 0;
        int n = order.length;

        Deque<Integer> boxes = new ArrayDeque<>();
        for(int i=1;i<=n;i++) {
            boxes.addLast(i);
        }

        Deque<Integer> sub = new ArrayDeque<>(); // stack

        for (int i=0;i<n;i++) {

            int target = order[i];

            if (!sub.isEmpty() && sub.peekLast() == target) {
                sub.pollLast();
                answer++;
                continue;
            }

            boolean flag = false;
            while(!boxes.isEmpty()) {

                if (boxes.peekFirst() == target) {
                    boxes.pollFirst();
                    answer++;
                    flag = true;
                    break;
                }

                sub.addLast(boxes.pollFirst());
            }


            if (!flag) {
                break;
            }
        }


        return answer;
    }
}