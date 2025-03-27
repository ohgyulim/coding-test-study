package dfs_bfs.level3;

class PGS_수레움직이기 {
    int[][] maze;
    boolean[][][] visited; //visited[r][c][0]: 빨간색이 r,c에 방문했는지 / 1은 파란색
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] maze) {
        this.maze = maze;
        visited = new boolean[maze.length][maze[0].length][2];

        int redR = 0;
        int redC = 0;
        int blueR = 0;
        int blueC = 0;

        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                if (maze[r][c] == 1) {
                    redR = r;
                    redC = c;
                } else if (maze[r][c] == 2) {
                    blueR = r;
                    blueC = c;
                }
            }
        }
        visited[redR][redC][0] = true;
        visited[blueR][blueC][1] = true;
        dfs(0, redR, redC, blueR, blueC);
        return answer != Integer.MAX_VALUE ? answer : 0;
    }

    public void dfs(int cnt, int redR, int redC, int blueR, int blueC) {
        if (maze[redR][redC] == 3 && maze[blueR][blueC] == 4) {
            answer = Math.min(answer, cnt);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int newRedR;
            int newRedC;
            if (maze[redR][redC] == 3) {
                newRedR = redR;
                newRedC = redC;
            } else {
                newRedR = redR + dr[i];
                newRedC = redC + dc[i];
            }

            if (isNotValid(newRedR, newRedC) || (visited[newRedR][newRedC][0] && maze[redR][redC] != 3)) continue;
            visited[newRedR][newRedC][0] = true;
            for (int j = 0; j < 4; j++) {
                int newBlueR;
                int newBlueC;
                if (maze[blueR][blueC] == 4) {
                    newBlueR = blueR;
                    newBlueC = blueC;
                } else {
                    newBlueR = blueR + dr[j];
                    newBlueC = blueC + dc[j];
                }
                if (isNotValid(newBlueR, newBlueC) || (visited[newBlueR][newBlueC][1] && maze[blueR][blueC] != 4))
                    continue;
                if ((newRedR == newBlueR && newRedC == newBlueC) ||
                        (newRedR == blueR && newRedC == blueC && newBlueR == redR && newBlueC == redC)) continue;

                visited[newBlueR][newBlueC][1] = true;
                dfs(cnt + 1, newRedR, newRedC, newBlueR, newBlueC);
                if (maze[blueR][blueC] == 4) {
                    break;
                }
                visited[newBlueR][newBlueC][1] = false;
            }

            if (maze[redR][redC] == 3) {
                break;
            }
            visited[newRedR][newRedC][0] = false;
        }
    }

    public boolean isNotValid(int r, int c) {
        return r < 0 || r >= maze.length || c < 0 || c >= maze[0].length || maze[r][c] == 5;
    }
}