package dfs_bfs.level2;

import java.util.LinkedList;
import java.util.Queue;

public class PGS_미로탈출 {
    public int solution(String[] maps) {
        int answer = 0;
        int height = maps.length;
        int width = maps[0].length();
        int sx = 0;
        int sy = 0;
        int lx = 0;
        int ly = 0;
        int ex = 0;
        int ey = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maps[i].charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                } else if (maps[i].charAt(j) == 'L') {
                    lx = i;
                    ly = j;
                } else if (maps[i].charAt(j) == 'E') {
                    ex = i;
                    ey = j;
                }

            }
        }


        int[][] visited = new int[height][width];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});
        visited[sx][sy] = 1;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        boolean flag = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= height || ny < 0 || ny >= width) {
                    continue;
                }

                if (maps[nx].charAt(ny) != 'X' && visited[nx][ny] <= visited[lx][ly]) {
                    if (maps[nx].charAt(ny) == 'L' && flag) {
                        queue.clear();
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = visited[x][y] + 1;
                        flag = false;
                        break;
                    } else if (maps[nx].charAt(ny) != 'L') {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = visited[x][y] + 1;
                    }
                }
            }
        }

        if (visited[lx][ly] == 0 || visited[ex][ey] == 0) {
            answer = -1;
        } else {
            answer = visited[ex][ey]-1;
        }

        return answer;
    }
}
