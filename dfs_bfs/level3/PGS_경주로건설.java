package dfs_bfs.level3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class PGS_경주로건설 {
    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;

        int[][] costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }
        costs[0][0] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, -1, 0});
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int prev_dir = cur[2];
            int curr_cost = cur[3];
            for (int i = 0; i < 4; i++) { // 상(0), 하(1), 좌(2), 우(3)
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 1) {
                    continue;
                }
                int cost;
                if (prev_dir == -1 || (prev_dir == i)) {
                    cost = curr_cost + 100;
                } else {
                    cost = curr_cost + 600;
                }
                if (costs[nx][ny] > cost - 500) {
                    costs[nx][ny] = Math.min(cost, costs[nx][ny]);
                    queue.offer(new int[]{nx, ny, i, cost});
                }

            }
        }
        answer = costs[n - 1][n - 1];
        return answer;
    }

}