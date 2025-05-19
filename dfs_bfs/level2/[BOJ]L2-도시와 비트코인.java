import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //보통 문제돠 다르게 m이 행, n이 열
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> queue = new LinkedList();
        boolean[][] visited = new boolean[n][m];
        queue.offer(new int[]{0,0});
        visited[0][0] = true;
        boolean isPossible = false;

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            if(x == n-1 && y == m-1) {
                isPossible = true;
                break;
            }

            if(x+1 <= n-1 && !visited[x+1][y] && map[x+1][y] == 1){
                queue.offer(new int[]{x+1,y});
                visited[x+1][y] = true;
            }
            if(y+1 <= m-1 && !visited[x][y+1] && map[x][y+1] == 1) {
                queue.offer(new int[]{x,y+1});
                visited[x][y+1] = true;
            }
        }
        //3항 연산자는 값을 반환가능 (값에 따라 다른 함수 실행은 불가능)
        //isPossible ? () : () 불가능
        System.out.println(isPossible? "Yes" : "No");

    }
}