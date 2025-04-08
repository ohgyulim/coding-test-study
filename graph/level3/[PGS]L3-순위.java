class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] graph = new boolean[n + 1][n + 1];

        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            graph[winner][loser] = true;
        }

        //플로이드 워셜
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    //A가B를 이기고, B가 C를 이기면
                    //A가 C를 이긴다는 간접승리도 판별
                    if (graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        int answer = 0;
        //선수마다 순위판별 가능한지 확인
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (graph[i][j] || graph[j][i]) {
                    count++;
                }
            }
            //해당 선수가 본인을 제외한 n-1명과 승부 판별이 가능(count의 수가 n-1과 ㄷ같음)하다면
            //해당 선수는 정확하게 순위를 매길 수 있음
            if (count == n - 1) answer++;
        }

        return answer;
    }
}