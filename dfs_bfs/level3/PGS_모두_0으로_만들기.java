package dfs_bfs.level3;

import java.util.*;

class PGS_모두_0으로_만들기 {

    public long solution(int[] a, int[][] edges) {

        long answer = 0;
        int n = a.length;
        long sum = 0;

        long[] aCopy = new long[n];
        int[] indegree = new int[n];

        for (int i=0;i<n;i++) {
            sum += a[i];
            aCopy[i] = a[i];
        }

        if (sum != 0) {
            return -1;
        }

        if (n == 2) {
            return Math.abs(a[0]);
        }

        if (sum == 0) { // 어떻게든 0을 만들 수는 있음. 최솟값 찾기.

            List<Integer>[] edge = new ArrayList[n];
            for (int i=0;i<n;i++) {
                edge[i] = new ArrayList<>();
            }

            for (int i=0;i<edges.length;i++) {
                edge[edges[i][0]].add(edges[i][1]);
                edge[edges[i][1]].add(edges[i][0]);

                indegree[edges[i][0]]++;
                indegree[edges[i][1]]++;
            }


            Deque<Integer> queue = new ArrayDeque<>();
            for (int i=1;i<n;i++) {
                if (indegree[i] == 1) {
                    queue.addLast(i);
                }
            }

            while(!queue.isEmpty()) {
                int now = queue.pollFirst();
                answer += Math.abs(aCopy[now]);
                indegree[now]--; // 부모 노드와 나를 끊는

                for (int nxt : edge[now]) {
                    if (indegree[nxt] == 0) { // 이미 볼일 끝난 노드다.
                        continue;
                    }

                    aCopy[nxt] += aCopy[now]; // 부모값에게 나에게 더하겠다.
                    indegree[nxt]--; // 부모와 나를 잇는 선을 끊음

                    if (indegree[nxt] == 1) { // 내 부모가 나를 끊음으로서 리프가 되었다.
                        if (nxt == 0) { // 루트를 그냥 무조건 0으로 가정하겠다.
                            continue;
                        }

                        queue.addLast(nxt);
                    }

                }

            }

        }

        return answer;
    }
}