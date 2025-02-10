package graph_search.level3;

import java.util.*;
import java.io.*;

public class BOJ_녹색옷입은애가젤다지 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        int number = 1;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            int[][] board = new int[N][N];
            int[][] visited = new int[N][N];

            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    board[r][c] = Integer.parseInt(st.nextToken());
                    visited[r][c] = Integer.MAX_VALUE;
                }
            }
            Deque<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0});
            visited[0][0] = board[0][0];

            while (!queue.isEmpty()) {
                int[] node = queue.poll();
                for (int d=0; d<4; d++) {
                    int nr = node[0] + dr[d];
                    int nc = node[1] + dc[d];

                    if (nr < 0 || nr >=N || nc < 0 || nc >= N) continue;
                    if (visited[node[0]][node[1]] + board[nr][nc] < visited[nr][nc]) {
                        visited[nr][nc] = visited[node[0]][node[1]] + board[nr][nc];
                        queue.offer(new int[] {nr, nc});
                    }
                }
            }
            System.out.println("Problem " + number + ": " + visited[N-1][N-1]);
            number ++;
        }
    }
}
