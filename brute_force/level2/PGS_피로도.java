package brute_force.level2;

class PGS_피로도 {
    public int solution(int k, int[][] dungeons) {
        int answer = 0;
        boolean[] visited = new boolean[dungeons.length];
        answer = recur(visited, k, dungeons);
        return answer;
    }

    private int recur(boolean[] visited, int tired, int[][] dungeons) {
        int cnt = 0;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] || dungeons[i][0] > tired) continue;
            visited[i] = true;
            cnt = Math.max(cnt, recur(visited, tired - dungeons[i][1], dungeons) + 1);
            visited[i] = false;
        }
        return cnt;
    }
}