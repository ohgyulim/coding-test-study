import java.util.*;
import java.io.*;

class Main {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] space = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[N][M][3]; // [행][열][이전 방향]

        // 초기화
        for (int j = 0; j < M; j++) {
            for (int d = 0; d < 3; d++) {
                dp[0][j][d] = space[0][j]; // 첫 줄은 방향 상관 없이 값만 넣음
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d = 0; d < 3; d++) { // 현재 방향
                    dp[i][j][d] = INF;
                    int prevCol = j + (d - 1); // 이전 열 위치 (왼 -1, 아래 0, 오 +1)
                    if (prevCol < 0 || prevCol >= M) continue;

                    for (int prevD = 0; prevD < 3; prevD++) {
                        if (prevD == d) continue; // 같은 방향 연속으로 못 씀
                        if (dp[i - 1][prevCol][prevD] != INF) {
                            dp[i][j][d] = Math.min(dp[i][j][d], dp[i - 1][prevCol][prevD] + space[i][j]);
                        }
                    }
                }
            }
        }

        // 마지막 줄에서 최소값 찾기
        int answer = INF;
        for (int j = 0; j < M; j++) {
            for (int d = 0; d < 3; d++) {
                answer = Math.min(answer, dp[N - 1][j][d]);
            }
        }

        System.out.println(answer);
    }
}
