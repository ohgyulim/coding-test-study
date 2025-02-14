package implementation.level2;

import java.util.*;

class PGS_행렬_테두리_회전 {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] map = new int[rows][columns];
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++) {
                map[i][j] = i * columns + j + 1;
            }
        }

        int[] dx = {1,0,-1,0}; // 하, 우, 상, 좌
        int[] dy = {0,1,0,-1};

        for (int i=0;i<queries.length;i++) {

            List<Integer> changed = new ArrayList<>();

            // 0-base
            int startX = queries[i][0]-1;
            int startY = queries[i][1]-1;
            int endX = queries[i][2]-1;
            int endY = queries[i][3]-1;

            int temp = map[startX][startY];
            changed.add(temp);

            int idx = 0;
            while(idx < 4) {
                int nx = startX + dx[idx];
                int ny = startY + dy[idx];

                if (!isInRange(queries[i], nx, ny)) {
                    idx++;
                    continue;
                }

                map[startX][startY] = map[nx][ny];
                changed.add(map[nx][ny]);

                startX = nx;
                startY = ny;
            }

            map[startX][startY+1] = temp;

            Collections.sort(changed);
            answer[i] = changed.get(0);
        }


        return answer;
    }

    public boolean isInRange(int[] range, int x, int y) {
        return range[0]-1 <= x && range[1]-1 <= y && range[2]-1 >= x && range[3]-1 >= y;
    }
}