import java.util.*;

class Solution {
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[][] dp;

    public int solution(int n, int[][] lighthouse) {
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        //[n][0] 끔
        //[n][1] 켬
        dp = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : lighthouse) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        // 1부터
        dfs(1);

        return Math.min(dp[1][0], dp[1][1]);
    }

    private void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0;
        dp[node][1] = 1;

        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next);

                // 꺼져있는경우 반대는 켜져야함
                // 켜져있는 경우 반대는 꺼져도 됨
                //
                dp[node][0] += dp[next][1];
                dp[node][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
}
