package dfs_bfs.level3;

import java.util.*;

class PGS_부대복귀 {

    private class Node {
        int idx;
        int time;
        public Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

        List<Integer>[] edges = new ArrayList[n+1];
        for (int i=1;i<=n;i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i=0;i<roads.length;i++) {
            int p = roads[i][0];
            int v = roads[i][1];

            edges[p].add(v);
            edges[v].add(p);
        }

        int[] answer = new int[sources.length];
        Arrays.fill(answer, -1);

        for (int i=0;i<sources.length;i++) {

            boolean[] visited = new boolean[n+1];

            Deque<Node> queue = new ArrayDeque<>();
            queue.addLast(new Node(sources[i], 0));
            visited[sources[i]] = true;


            while(!queue.isEmpty()) {

                Node now = queue.pollFirst();

                if (now.idx == destination) {
                    answer[i] = now.time;
                    break;
                }

                for (int idx : edges[now.idx]) {

                    if (!visited[idx]) {
                        visited[idx] = true;
                        queue.addLast(new Node(idx, now.time + 1));
                    }
                }

            }

        }

        return answer;
    }

}