package simulation.level3;

import java.io.*;
import java.util.*;

public class BOJ_새로운_게임{
    private static class ChessMen {
        int x, y;
        int dir;
        public ChessMen(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    private final static int WHITE = 0, RED = 1, BLUE = 2;
    private final static int changeDir[] = {2, 1, 4, 3};
    private final static int[] dx = {0, 0, -1, 1};
    private final static int[] dy = {1, -1, 0, 0};
    private static int[][] map;
    private static Deque<Integer>[][] state;
    private static ChessMen[] chessMen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];
        state = new ArrayDeque[n + 1][n + 1];
        chessMen = new ChessMen[k];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                state[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            chessMen[i] = new ChessMen(x, y, dir);
            state[x][y].addLast(i);
        }

        int ans = move(n, k);
        System.out.println(ans);
    }

    public static int move(int n, int k){
        int times = 0;
        boolean isEnd = false;

        while (!isEnd) {
            times++;

            if (times > 1000) {
                break;
            }

            for (int i = 0; i < k; i++) {
                ChessMen curr = chessMen[i];
                int x = curr.x;
                int y = curr.y;

                if (state[x][y].peekFirst() != i) {
                    continue;
                }

                int nx = x + dx[curr.dir - 1];
                int ny = y + dy[curr.dir - 1];

                if (!isInRange(nx, ny, n) || map[nx][ny] == BLUE) {
                    curr.dir = changeDir[curr.dir - 1];
                    nx = x + dx[curr.dir - 1];
                    ny = y + dy[curr.dir - 1];
                }

                if (!isInRange(nx, ny, n) || map[nx][ny] == BLUE) { // 방향 바꾸고 난 후 -> 다시 파라색
                    continue;
                }
                else if (map[nx][ny] == RED) {
                    while (!state[x][y].isEmpty()) {
                        int idx = state[x][y].pollLast();
                        state[nx][ny].addLast(idx);
                        chessMen[idx].x = nx;
                        chessMen[idx].y = ny;
                    }
                }
                else {
                    while (!state[x][y].isEmpty()) {
                        int idx = state[x][y].pollFirst();
                        state[nx][ny].addLast(idx);
                        chessMen[idx].x = nx;
                        chessMen[idx].y = ny;
                    }
                }

                if (state[nx][ny].size() >= 4) { // 게임 종료 조건
                    isEnd = true;
                    break;
                }
            }
        }

        return isEnd ? times : -1;
    }

    public static boolean isInRange(int x, int y, int n) {
        return (x >= 1 && y >= 1 && x <= n && y <= n);
    }
}
