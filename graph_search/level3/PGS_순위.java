package graph_search.level3;

class PGS_순위 {
    public int solution(int n, int[][] results) {
        int answer = 0;

        int[][] winloseBoard = new int[n + 1][n + 1];

        for (int[] result : results) {
            int A = result[0];
            int B = result[1];

            winloseBoard[A][B] = 1;
            winloseBoard[B][A] = -1;
        }

        for (int k = 1; k <= n; k++) { // i가 k를 이기고 k가 j를 이기는지 확인
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == k || j == k || i == j) continue;
                    if (winloseBoard[i][k] == 1 && winloseBoard[k][j] == 1) {
                        winloseBoard[i][j] = 1;
                        winloseBoard[j][i] = -1;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int cnt0 = -1;
            for (int j : winloseBoard[i]) {
                if (j == 0) cnt0++;
            }
            if (cnt0 == 1) {
                answer++;
            }
        }

        return answer;
    }
}