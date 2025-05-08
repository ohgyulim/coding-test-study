import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int r = maps.length;
        int c = maps[0].length;

        Queue<int[]> queue = new LinkedList();
        boolean[][] visited = new boolean[r][c];

        queue.offer(new int[]{0,0});

        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for(int i = 0; i < dx.length; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && ny < c && ny >= 0 && nx < r && !visited[nx][ny] && maps[nx][ny] == 1){
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx,ny});
                    maps[nx][ny] = maps[x][y]+1;
                }
            }
        }
        int answer = maps[r-1][c-1];
        return answer == 1 ? -1 : answer;
    }
}