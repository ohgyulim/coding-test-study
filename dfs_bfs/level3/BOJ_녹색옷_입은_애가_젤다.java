import java.util.*;
import java.io.*;

class Main{
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{

        int t = 1;
        while(true){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int N =Integer.parseInt(st.nextToken());
            if(N==0) break;

            int[][] cave = inputArr(N);

            System.out.println("Problem "+t+": "+bfs(cave, N));
            t++;
        }
    }

    private static int bfs(int[][] cave, int N){
        int min = Integer.MAX_VALUE;

        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};

        int[][] visited = new int[N][N];
        visited[0][0] = cave[0][0];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0,0});

        while(!queue.isEmpty()){
            int[] pos = queue.poll();
            int row = pos[0];
            int col = pos[1];
            int rupee = visited[row][col];
//            System.out.println(row+", "+col+", "+rupee);

            if(row==N-1 && col==N-1){
                min = Math.min(min, rupee);
                continue;
            }

            for(int i=0;i<4;i++){
                int r = row+dr[i];
                int c = col+dc[i];

                if(r<0 || r>=N || c<0 || c>=N) continue;

                if(visited[r][c] > 0 && rupee+cave[r][c] < visited[r][c]){
                    visited[r][c]=rupee+cave[r][c];
                    queue.add(new int[] {r,c});
                    continue;
                }

                if(visited[r][c] == 0){
                    visited[r][c]+=rupee+cave[r][c];
                    queue.add(new int[] {r,c});
                    continue;
                }
            }
        }

        return min;
    }

    private static int[][] inputArr(int N) throws IOException{
        int[][] arr = new int[N][N];

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return arr;
    }
}