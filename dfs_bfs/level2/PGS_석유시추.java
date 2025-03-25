package dfs_bfs.level2;

import java.util.*;

class PGS_석유시추 {
    class Oil {
        int value;

        public Oil(int v) {
            value = v;
        }
    }

    public int solution(int[][] land) {
        int answer = 0;

        Oil[][] visited = new Oil[land.length][land[0].length];
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (visited[i][j] != null || land[i][j] == 0) continue;

                Deque<int[]> queue = new LinkedList<>();
                queue.offer(new int[]{i, j});
                visited[i][j] = new Oil(1);

                while (!queue.isEmpty()) {
                    int[] pos = queue.poll();
                    int r = pos[0];
                    int c = pos[1];
                    Oil oil = visited[r][c];

                    for (int k = 0; k < 4; k++) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        if (nr < 0 || nr >= land.length || nc < 0 || nc >= land[0].length ||
                                land[nr][nc] == 0 || visited[nr][nc] != null) continue;
                        queue.offer(new int[]{nr, nc});
                        oil.value++;
                        visited[nr][nc] = oil;
                    }
                }
            }
        }

//         for (int r=0; r<land.length; r++) {
//             for (int c=0; c<land[0].length; c++) {
//                 if (visited[r][c] == null) {
//                     System.out.print(0 + " ");
//                 } else {
//                     System.out.print(visited[r][c].value + " ");
//                 }

//             }
//             System.out.println();
//         }

        for (int c = 0; c < land[0].length; c++) {
            int sum = 0;
            Set<Oil> set = new HashSet<>();
            for (int r = 0; r < land.length; r++) {
                if (visited[r][c] == null || set.contains(visited[r][c])) continue;
                sum += visited[r][c].value;
                set.add(visited[r][c]);
            }

            answer = Math.max(answer, sum);
        }

        return answer;
    }
}