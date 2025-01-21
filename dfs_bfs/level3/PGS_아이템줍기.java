package dfs_bfs.level3;

import java.util.*;

class PGS_아이템줍기 {
    boolean[][] board = new boolean[101][101];
    int[][] visited = new int[101][101];
    int[] dy = new int[]{1, -1, 0, 0};
    int[] dx = new int[]{0, 0, 1, -1};

    int[] dy2 = new int[]{2, -2, 0, 0};
    int[] dx2 = new int[]{0, 0, 2, -2};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        initBoard(rectangle);

        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{characterX * 2, characterY * 2});
        visited[characterY * 2][characterX * 2] = 1;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int x = node[0];
            int y = node[1];
            if (x == 2 * itemX && y == 2 * itemY) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                int nx2 = x + dx2[i];
                int ny2 = y + dy2[i];

                if (nx < 0 || nx > 100 || ny < 0 || ny > 100 || !board[ny][nx] || visited[ny2][nx2] > 0) continue;
                visited[ny][nx] = visited[y][x] + 1;
                visited[ny2][nx2] = visited[y][x] + 1;
                queue.offer(new int[]{nx2, ny2});
            }
        }
        answer = visited[2 * itemY][2 * itemX] - 1;
        return answer;
    }

    private void initBoard(int[][] rectangleArray) {
        // 모든 테두리를 true로 바꿈
        boolean[][] inner = new boolean[101][101];
        for (int[] rectangle : rectangleArray) {
            int lx = rectangle[0];
            int ly = rectangle[1];
            int rx = rectangle[2];
            int ry = rectangle[3];

            for (int x = 2 * lx; x <= 2 * rx; x++) {
                for (int y = 2 * ly; y <= 2 * ry; y++) {
                    if (x == 2 * lx || x == 2 * rx || y == 2 * ly || y == 2 * ry) { // 테두리일 때
                        board[y][x] = true;
                    } else { // 사각형 내부일 때;
                        inner[y][x] = true;
                    }
                }
            }
        }

        // board의 0인 값의 좌표가 사각형의 내부인지 확인
        for (int x = 0; x <= 100; x++) {
            for (int y = 0; y <= 100; y++) {
                if (inner[y][x]) {
                    board[y][x] = false;
                }
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}