import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < progresses.length; i++) {
            int days = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            queue.offer(days);
        }

        while (!queue.isEmpty()) {
            int deployDay = queue.poll();
            int count = 1;

            while (!queue.isEmpty() && queue.peek() <= deployDay) {
                queue.poll();
                count++;
            }

            result.add(count);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}