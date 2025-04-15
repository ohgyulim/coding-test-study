package dfs_bfs.level3;

class PGS_네트워크 {
    int N;
    int[][] graph;
    boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        N = n;
        graph = computers;
        visited = new boolean[n];
        for (int i=0;i<n;i++){
            if (!visited[i]){
                dfs(i);
                answer ++;
            }
        }
        return answer;
    }
    void dfs(int node){
        visited[node] = true;
        for (int i=0;i<N;i++) {
            if (graph[node][i] == 1 && node != i && !visited[i]) {
                dfs(i);
            }
        }
    }
}