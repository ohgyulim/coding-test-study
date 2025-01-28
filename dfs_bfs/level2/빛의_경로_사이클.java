import java.util.*;

public class 빛의_경로_사이클 {
    static int[] dy = {-1,0,1,0};  //상 우 하 좌 (시계방향)
    static int[] dx = {0,1,0,-1};

    static int R;
    static int C;

    static boolean[][][] arr;

    public List<Integer> solution(String[] grid) {

        List<Integer> list = new ArrayList<>();
        R = grid.length;
        C = grid[0].length();

        arr = new boolean[R][C][4];   // 내가 쏜 방향 방문체크

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                for(int d=0; d<4; d++){ //방향
                    if(!arr[i][j][d]){
                        list.add(search(i,j,d,grid));
                    }
                }
            }
        }
        Collections.sort(list);
        return list;
    }

    public int search(int r, int c, int d, String[] grid){
        // arr[r][c][d] = true;
        int count =0;

        while(true){
            //한바퀴 순환됐으면 return
            if(arr[r][c][d]){
                return count;
            }
            arr[r][c][d] = true;    // 해당방향으로 쏘기
            count++;

            //도착지점의 방향 전환
            if(grid[r].charAt(c)=='L'){
                d = (d+3) % 4;
            }
            else if(grid[r].charAt(c)=='R'){
                d = (d+1) % 4;
            }

            // 좌표 이동
            r = (r + dy[d]+R) % R;  //음수 방지 조심
            c = (c + dx[d]+C) % C;

        }
    }
}
