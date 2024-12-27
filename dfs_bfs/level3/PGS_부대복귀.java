package dfs_bfs.level3;

import java.util.*;

public class PGS_부대복귀 {

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        List<List<Integer>> graph = new ArrayList<>(); // i와 연결된 지역을
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int[] visited = new int[n + 1]; // destination부터 i까지의 거리
        Arrays.fill(visited, -1);
        visited[destination] = 0;

        Deque<Integer> queue = new LinkedList<>();
        queue.offer(destination);

        while (!queue.isEmpty()) {
            int x = queue.pollFirst();
            List<Integer> regions = graph.get(x);
            for (int region : regions) {
                if (visited[region] == -1) {
                    visited[region] = visited[x] + 1;
                    queue.offer(region);
                }
            }
        }

        for (int i = 0; i < sources.length; i++) {
            answer[i] = visited[sources[i]];
        }
        return answer;
    }
}
