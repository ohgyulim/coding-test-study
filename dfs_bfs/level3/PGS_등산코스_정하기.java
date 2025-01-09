package dfs_bfs.level3;

import java.util.*;

class PGS_등산코스_정하기 {
    private class Node {
        int num, time;
        public Node(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        List<Node>[] edges = new ArrayList[n+1];
        for (int i=1;i<=n;i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i=0;i<paths.length;i++) {
            int p = paths[i][0];
            int v = paths[i][1];
            int time = paths[i][2];

            edges[p].add(new Node(v, time));
            edges[v].add(new Node(p, time));

        }

        return answer;
    }
}