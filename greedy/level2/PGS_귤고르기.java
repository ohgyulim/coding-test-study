package greedy.level2;

import java.util.*;

public class PGS_귤고르기 {
    class Node implements Comparable<Node> {
        int size;
        int count;

        public Node(int size, int count) {
            this.size = size;
            this.count = count;
        }

        @Override
        public int compareTo(Node node) {
            return node.count - this.count;
        }
    }

    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int size : tangerine) {
            if (map.containsKey(size)) {
                map.put(size, map.get(size) + 1);
            } else {
                map.put(size, 1);
            }
        }

        for (int key : map.keySet()) {
            pq.offer(new Node(key, map.get(key)));
        }

        while (true) {
            Node node = pq.poll();
            if (node.count >= k) {
                answer += 1;
                break;
            }
            answer += 1;
            k -= node.count;
        }


        return answer;
    }
}
