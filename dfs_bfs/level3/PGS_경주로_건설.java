package dfs_bfs.level3;

import java.util.*;

class PGS_경주로_건설 {

    private static class Road {
        int r,c;
        int dir;

        public Road(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    private static final int[] dr = {-1,1,0,0}; // 상하좌우
    private static final int[] dc = {0,0,-1,1};
    private static int[][][] cost;

    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        cost = new int[n][n][2];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }

        Deque<Road> queue = new ArrayDeque<>();
        if (board[1][0] != 1) {
            cost[1][0][0] = 100;
            queue.addLast(new Road(1,0,0));
        }

        if (board[0][1] != 1) {
            cost[0][1][1] = 100;
            queue.addLast(new Road(0,1,1));
        }


        while(!queue.isEmpty()) {

            Road now = queue.pollFirst();

            for (int i=0;i<4;i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];


                if (nr < 0 || nc < 0 || nr >= n || nc >= n || board[nr][nc] == 1) {
                    continue;
                }

                if (now.dir == 0) { // 현재 진행방향이 상하일 때

                    if (i < 2 && cost[nr][nc][0] > cost[now.r][now.c][0] + 100) { // 상하이동(방향 바뀌지 x)
                        cost[nr][nc][0] = cost[now.r][now.c][0] + 100;
                        queue.addLast(new Road(nr,nc,0));
                    }

                    if (i>= 2 && cost[nr][nc][1] > cost[now.r][now.c][0] + 600) { // 좌우 이동(방향 바뀜)
                        cost[nr][nc][1] = cost[now.r][now.c][0] + 600;
                        queue.addLast(new Road(nr,nc,1));
                    }
                }
                else { // 현재 진행방향이 좌우 일 때
                    if (i < 2 && cost[nr][nc][0] > cost[now.r][now.c][1] + 600) { // 상하이동(방향 바뀜)
                        cost[nr][nc][0] = cost[now.r][now.c][1] + 600;
                        queue.addLast(new Road(nr,nc,0));
                    }

                    if (i>= 2 && cost[nr][nc][1] > cost[now.r][now.c][1] + 100) { // 좌우 이동(방향 바뀌지 x)
                        cost[nr][nc][1] = cost[now.r][now.c][1] + 100;
                        queue.addLast(new Road(nr,nc,1));
                    }
                }
            }

        }

        answer = Math.min(cost[n-1][n-1][0], cost[n-1][n-1][1]);


        return answer;
    }
}