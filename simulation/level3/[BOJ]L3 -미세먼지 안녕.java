import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //2차원 배열 r,c
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int[][] home = new int[r][c];
        int over = 0;
        int under = 0;
        Queue<int[]> queue = new LinkedList<>();

        //확산할 칸 정보 {x,y,먼지값} 큐에 저장
        //공기 청정기 위치 저장
        //집의 먼지 분포 저장
        for(int i = 0; i < r; i++){
            String[] str = br.readLine().split(" ");
            for(int j = 0; j < str.length; j++){
                home[i][j] = Integer.parseInt(str[j]);
                if (home[i][j] == -1 && over == 0) over = i;
                else if (home[i][j] == -1 && over != 0) under = i;
                else if (home[i][j] > 0 && home[i][j]/5 >= 1) queue.add(new int[]{i,j});
            }
        }

        //T초 동안 반복
        for(int i = 0; i < t; i++){
            spread(home,queue);
            turnOnOver(home,over);
            turnOnUnder(home,under);

            //1회 반복 끝나면 다음 반복을 위한 작업 리스트를 큐에 담는다.
            findJob(home,queue);
        }
        System.out.println(countDust(home));

    }
    //매초 일어나는일
    //미세먼지가 있는 타일 인접 타일로 확산 (확산량은 Ar,c/5)
    //기존 미세먼지는 Ar,c - Ar,c/5 *확산 방향 개수
    //공기청정기 있거나 밖이면 확산x
    //임시 배열에 안담고 하나씩 순차 계산하니까 확산된 값이 다음 값 확산에 영향줘서 계속 이상했음
    //확산량을 임시 배열에 한방에 계산해두고 원래 값에 더해주는 방식으로 구현
    static void spread(int[][] home, Queue<int[]> queue){
        int[] nx = {0, 0, -1, 1};
        int[] ny = {1, -1, 0, 0};

        // 미세먼지 확산 결과를 저장할 임시 배열
        int[][] spreadHome = new int[home.length][home[0].length];

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];

            int spreadCnt = 0;

            // 각 방향으로 확산
            for(int i = 0; i < 4; i++){
                int newX = curX + nx[i];
                int newY = curY + ny[i];

                // 확산 가능하면
                if(newX >= 0 && newX < home.length && newY >= 0 && newY < home[0].length && home[newX][newY] != -1){
                    int spreadAmount = home[curX][curY] / 5;
                    spreadHome[newX][newY] += spreadAmount; // 확산량을 임시 배열에 추가
                    spreadCnt++;
                }
            }

            // 현재 칸에서 빠져나간 미세먼지 양을 차감
            home[curX][curY] -= home[curX][curY] / 5 * spreadCnt;
        }

        // 임시 배열에서 확산된 값을 home 배열에 반영
        for (int i = 0; i < home.length; i++) {
            for (int j = 0; j < home[0].length; j++) {
                home[i][j] += spreadHome[i][j]; // 확산된 값 반영
            }
        }
    }

    //공기 청정기 작동
    //윗 공기 청정기는 반시계 방향으로 순환 -
    //아래는 시계방향으로 순환
    static void turnOnOver(int[][] home, int over){
        int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌
        int[] dy = {0, 1, 0, -1}; // 상 우 하 좌
        int startX = over, startY = 0;
        int curX = startX, curY = startY;

        // 상 우 하 좌 순으로 순환
        for (int i = 0; i < 4; i++) {
            while (true) {
                int newX = curX + dx[i];
                int newY = curY + dy[i];

                // 벽을 만나거나 시작점에 돌아오면 종료
                if (newX < 0 || newX >= over+1 || newY < 0 || newY >= home[0].length || (newX == startX && newY == startY)) {
                    if(newX == startX && newY == startY){
                        home[curX][curY] = 0;
                    }
                    break;
                }

                if(curX == startX && curY == startY){
                    curX = newX;
                    curY = newY;
                }else{
                    home[curX][curY] = home[newX][newY];
                    curX = newX;
                    curY = newY;
                }
            }
        }
    }

    static void turnOnUnder(int[][] home, int under){
        int[] dx = {1, 0, -1, 0}; // 하 우 상 좌
        int[] dy = {0, 1, 0, -1}; // 하 우 상 좌
        int startX = under, startY = 0;
        int curX = startX, curY = startY;

        // 우 → 하 → 좌 → 상 순으로 순환
        for (int i = 0; i < 4; i++) {
            while (true) {
                int newX = curX + dx[i];
                int newY = curY + dy[i];

                // 벽을 만나거나 시작점에 돌아오면 종료
                if (newX < under || newX >= home.length || newY < 0 || newY >= home[0].length || (newX == startX && newY == startY)) {
                    if(newX == startX && newY == startY){
                        home[curX][curY] = 0;
                    }
                    break;
                }

                if(curX == startX && curY == startY){
                    curX = newX;
                    curY = newY;
                }else{
                    home[curX][curY] = home[newX][newY];
                    curX = newX;
                    curY = newY;
                }
            }
        }
    }

    static int countDust(int[][] home){
        int sum = 0;
        for(int i = 0; i < home.length; i++){
            for(int j = 0; j < home[0].length; j++){
                if(home[i][j] > 0){
                    sum += home[i][j];
                }
            }
        }
        return sum;
    }

    static void findJob(int[][] home, Queue<int[]> queue){
        //다시 전체 돌면서 확산 대상이 되는 위치를 큐에 삽입
        for(int i = 0; i < home.length; i++){
            for(int j = 0; j < home[0].length; j++){
                if (home[i][j] > 0 && home[i][j]/5 >= 1){
                    queue.add(new int[]{i,j});
                }
            }
        }
    }
}