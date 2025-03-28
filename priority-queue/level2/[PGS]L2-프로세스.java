import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> queue = new LinkedList<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위 큐 (내림차순 정렬)

        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new int[]{i, priorities[i]});
            priorityQueue.offer(priorities[i]);
        }

        int count = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int index = current[0];
            int priority = current[1];

            if (priority < priorityQueue.peek()) {
                queue.offer(current);
            } else {
                count++;
                priorityQueue.poll();

                if (index == location) {
                    return count;
                }
            }
        }

        return -1;
    }
}