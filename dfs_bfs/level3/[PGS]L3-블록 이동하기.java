import java.util.*;

class Solution {
    // 상하좌우
    private static final int[][] MOVE_DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 가로->세로, 세로->가로
    private static final int[][] ROTATE_DIRECTIONS = {{1, -1}, {1, 1}, {-1, -1}, {-1, 1}};

    static class Robot {
        int r1, c1, r2, c2, time;

        Robot(int r1, int c1, int r2, int c2, int time) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.time = time;
        }
    }

    public int solution(int[][] board) {
        int N = board.length;
        //0가로 1세로
        boolean[][][] visited = new boolean[N][N][2];

        Queue<Robot> queue = new LinkedList<>();
        queue.add(new Robot(0, 0, 0, 1, 0));
        visited[0][0][0] = true;
        visited[0][1][0] = true;

        while (!queue.isEmpty()) {
            Robot cur = queue.poll();

            // 목표 도착?
            if ((cur.r1 == N - 1 && cur.c1 == N - 1) || (cur.r2 == N - 1 && cur.c2 == N - 1)) {
                return cur.time;
            }

            // 상하좌우
            for (int[] dir : MOVE_DIRECTIONS) {
                int nr1 = cur.r1 + dir[0], nc1 = cur.c1 + dir[1];
                int nr2 = cur.r2 + dir[0], nc2 = cur.c2 + dir[1];

                if (isValidMove(nr1, nc1, nr2, nc2, board, N)) {
                    int dirIdx = (cur.r1 == cur.r2) ? 0 : 1;
                    if (!visited[nr1][nc1][dirIdx] || !visited[nr2][nc2][dirIdx]) {
                        visited[nr1][nc1][dirIdx] = true;
                        visited[nr2][nc2][dirIdx] = true;
                        queue.add(new Robot(nr1, nc1, nr2, nc2, cur.time + 1));
                    }
                }
            }

            // 회전
            rotateAndAdd(cur, queue, visited, board, N);
        }

        return -1;
    }

    // 유효한 이동인지 체크
    private boolean isValidMove(int r1, int c1, int r2, int c2, int[][] board, int N) {
        return (r1 >= 0 && r1 < N && c1 >= 0 && c1 < N && board[r1][c1] == 0) &&
                (r2 >= 0 && r2 < N && c2 >= 0 && c2 < N && board[r2][c2] == 0);
    }

    // 회전
    private void rotateAndAdd(Robot cur, Queue<Robot> queue, boolean[][][] visited, int[][] board, int N) {
        int[][] rotatePoints = (cur.r1 == cur.r2) ? new int[][]{{-1, 0}, {1, 0}} : new int[][]{{0, -1}, {0, 1}};

        for (int[] point : rotatePoints) {
            int nr1 = cur.r1 + point[0], nc1 = cur.c1 + point[1];
            int nr2 = cur.r2 + point[0], nc2 = cur.c2 + point[1];

            if (isValidMove(nr1, nc1, nr2, nc2, board, N)) {
                int newDir = (cur.r1 == cur.r2) ? 1 : 0;

                // 첫 번째 칸 중심
                if (!visited[cur.r1][cur.c1][newDir]) {
                    visited[cur.r1][cur.c1][newDir] = true;
                    queue.add(new Robot(cur.r1, cur.c1, nr1, nc1, cur.time + 1));
                }

                // 두 번째 칸 중심
                if (!visited[cur.r2][cur.c2][newDir]) {
                    visited[cur.r2][cur.c2][newDir] = true;
                    queue.add(new Robot(cur.r2, cur.c2, nr2, nc2, cur.time + 1));
                }
            }
        }
    }
}
