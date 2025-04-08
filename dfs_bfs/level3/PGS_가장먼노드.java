package dfs_bfs.level3;

import java.util.*;

class PGS_가장먼노드 {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        Set<Integer>[] linkedNode = new HashSet[n + 1];
        for (int i = 0; i < n + 1; i++) {
            linkedNode[i] = new HashSet<>();
        }


        for (int[] e : edge) {
            linkedNode[e[0]].add(e[1]);
            linkedNode[e[1]].add(e[0]);
        }


        boolean[] visited = new boolean[n + 1];
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            answer = 0;
            int m = queue.size();
            for (int i = 0; i < m; i++) {
                int node = queue.poll();
                answer++;
                for (int newNode : linkedNode[node]) {
                    if (visited[newNode]) continue;
                    visited[newNode] = true;
                    queue.offer(newNode);
                }
            }
        }
        return answer;
    }
}