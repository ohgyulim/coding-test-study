package dfs_bfs.level2;

import java.util.LinkedList;

class PGS_타켓넘버 {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        int x;
        for (int i = 0; i < numbers.length; i++) {
            int queueLength = queue.size();
            for (int j = 0; j < queueLength; j++) {
                x = queue.poll();
                queue.add(x - numbers[i]);
                queue.add(x + numbers[i]);
            }
        }
        while (!queue.isEmpty()) {
            if (queue.pop() == target) {
                answer++;
            }
        }
        return answer;
    }
}