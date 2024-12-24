import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static char[][] grid;
    static String[] targets;
    static int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new char[N][M];
        for (int i = 0; i < N; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        targets = new String[K];
        for (int i = 0; i < K; i++) {
            targets[i] = br.readLine();
        }

        for (String target : targets) {
            dp = new int[N][M][target.length()];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }

            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    count += dfs(i, j, target, 0);
                }
            }
            System.out.println(count);
        }
    }

    static int dfs(int x, int y, String target, int idx) {
        if (idx == target.length()) {
            return 1;
        }

        if (dp[x][y][idx] != -1) {
            return dp[x][y][idx];
        }

        int count = 0;
        if (grid[x][y] == target.charAt(idx)) {
            for (int[] dir : directions) {
                int nx = (x + dir[0] + N) % N;
                int ny = (y + dir[1] + M) % M;
                count += dfs(nx, ny, target, idx + 1);
            }
        }

        dp[x][y][idx] = count;
        return count;
    }
}
