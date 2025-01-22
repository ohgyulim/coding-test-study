import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int size = 101;
        boolean[][] map = new boolean[size][size];
        boolean[][] visited = new boolean[size][size];

        // 좌표를 2배로 확장하여 경계 처리
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;

            // 직사각형 테두리만 true로 설정
            for (int x = x1; x <= x2; x++) {
                map[x][y1] = true;
                map[x][y2] = true;
            }
            for (int y = y1; y <= y2; y++) {
                map[x1][y] = true;
                map[x2][y] = true;
            }
        }

        // 내부를 false 처리 (테두리만 남기기)
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;

            for (int x = x1 + 1; x < x2; x++) {
                for (int y = y1 + 1; y < y2; y++) {
                    map[x][y] = false;
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { characterX * 2, characterY * 2, 0 });
        visited[characterX * 2][characterY * 2] = true;

        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int distance = current[2];

            if (x == itemX * 2 && y == itemY * 2) {
                return distance / 2;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < size && ny >= 0 && ny < size && map[nx][ny] && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[] { nx, ny, distance + 1 });
                }
            }
        }

        return -1;
    }
}
