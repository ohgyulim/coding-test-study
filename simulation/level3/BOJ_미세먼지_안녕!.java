package simulation.level3;

import java.util.*;
import java.io.*;


public class BOJ_미세먼지_안녕 {
    private static class Dust {
        int r,c;

        public Dust(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int R,C,T;
    private static int[][] map;
    private static List<Integer> airPurifier;
    private static Deque<Dust> dust;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        airPurifier = new ArrayList<>();

        for (int i=0;i<R;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<C;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    airPurifier.add(i);
                }
            }
        }

        for (int t=0;t<T;t++) {
            simulate();
        }

        int answer = getTotaldust();
        System.out.println(answer);
    }

    public static void simulate() {
        findDust();

        diffusion(); // 확산
        rotateLeft(); // 반시계방향 회전
        rotateRight(); // 시계방향 회전
    }

    public static void findDust() {

        dust = new ArrayDeque<>();

        for (int i=0;i<R;i++) {
            for (int j=0;j<C;j++) {
                if (map[i][j] != 0 && map[i][j] != -1) {
                    dust.addLast(new Dust(i,j));
                }
            }
        }
    }

    public static void diffusion(){

        int[] dr = {1,-1,0,0};
        int[] dc = {0,0,1,-1};

        while (!dust.isEmpty()) {

            Dust now = dust.pollFirst();
            int amount = map[now.r][now.c];
            int dirCnt = 0;

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];

                if (!isInRange(nr, nc) || map[nr][nc] == -1) {
                    continue;
                }

                dirCnt++;
                map[nr][nc] += amount/5;
            }

            map[now.r][now.c] -= (amount/5) * dirCnt;
        }
    }

    public static boolean isInRange(int r, int c) {
        return r>=0 && c >= 0 && r < R && c < C;
    }

    public static void rotateLeft() {
        int topRow = airPurifier.get(0);

        for (int r = topRow-1; r > 0; r--) {
            map[r][0] = map[r-1][0];
        }

        for (int c=0;c<C-1;c++) {
            map[0][c] = map[0][c+1];
        }

        for (int r = 0; r < topRow; r++) {
            map[r][C-1] = map[r+1][C-1];
        }

        for (int c=C-1; c > 1; c--) {
            map[topRow][c] = map[topRow][c-1];
        }

        map[topRow][1] = 0;
    }


    public static void rotateRight(){
        int bottomRow = airPurifier.get(1);
        for (int r = bottomRow + 1; r < R-1;r++) {
            map[r][0] = map[r+1][0];
        }

        for (int c=0;c<C-1;c++) {
            map[R-1][c] = map[R-1][c+1];
        }

        for (int r=R-1;r>bottomRow;r--) {
            map[r][C-1] = map[r-1][C-1];
        }

        for (int c = C-1;c>1;c--) {
            map[bottomRow][c] = map[bottomRow][c-1];
        }

        map[bottomRow][1] = 0;
    }

    public static int getTotaldust(){
        int total = 0;
        for (int i=0;i<R;i++) {
            for (int j=0;j<C;j++) {
                if (map[i][j] != -1 && map[i][j] != 0) {
                    total += map[i][j];
                }
            }
        }
        return total;
    }
}