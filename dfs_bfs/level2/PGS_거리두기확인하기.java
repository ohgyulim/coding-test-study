package dfs_bfs.level2;

import java.util.*;

class PGS_거리두기확인하기 {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);
        for (int i = 0; i < places.length; i++) {
            char[][] placeArray = getPlaceArray(places[i]);
            boolean flag = false;
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (placeArray[r][c] == 'P' && bfs(placeArray, r, c) == 0) {
                        answer[i] = 0;
                        flag = true;
                        break;
                    }
                }
                if (flag) break;
            }
        }

        return answer;
    }

    private char[][] getPlaceArray(String[] place) {
        char[][] placeArray = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                placeArray[i][j] = place[i].charAt(j);
            }
        }
        return placeArray;
    }

    private int bfs(char[][] placeArray, int r, int c) {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        boolean[][] visited = new boolean[5][5];
        visited[r][c] = true;
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});

        for (int d = 1; d <= 2; d++) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] node = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int nr = node[0] + dr[k];
                    int nc = node[1] + dc[k];
                    if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || visited[nr][nc] || placeArray[nr][nc] == 'X')
                        continue;
                    if (placeArray[nr][nc] == 'P') {
                        return 0;
                    }
                    queue.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }

        }
        return 1;
    }
}