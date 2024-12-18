package simulation.level3;

import java.util.*;
import java.io.*;

public class BOJ_낚시왕 {

    private static class Shark {
        int r,c,s;
        int dir;
        int size;
        public Shark(int r, int c, int s, int dir, int size) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.dir = dir;
            this.size = size;
        }
    }

    private static int R,C,M;
    private static Deque<Shark> sharks;
    private static int[][] map;

    private static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[R+1][C+1];
        sharks = new ArrayDeque<>();

        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            sharks.addLast(new Shark(r, c, s, d, z));

            map[r][c]++;
        }

        if (M != 0) {
            for (int c=1;c<=C;c++) {
                simulate(c);
            }
        }

        System.out.println(answer);
    }

    public static void simulate(int col) {
        catchTarget(col);  // 1) 상어 잡기
        moveShark();  // 2) 상어 이동
        remove(); // 3) 상어끼리 잡아먹기
    }

    public static void catchTarget(int col) {
        int row = -1;
        for (int i=1;i<=R;i++) {
            if (map[i][col] == 1) { // 잡을 상어가 있음
                row = i;
                break;
            }
        }

        if (row == -1) { // 잡을 상어가 없음
            return;
        }

        int size = sharks.size();
        for (int i=0;i<size;i++) {
            Shark s = sharks.pollFirst();
            if (s.r == row && s.c == col) {
                map[s.r][s.c]--;
                answer += s.size;
                break;
            }
            sharks.addLast(s);
        }
    }

    public static void moveShark() {
        int[] dr = {-1,1,0,0}; // 위 아래 오 왼
        int[] dc = {0,0,1,-1};

        int size = sharks.size();
        for (int i=0;i<size;i++) {
            Shark now = sharks.pollFirst();
            map[now.r][now.c]--;

            int dir = now.dir;
            int nr = now.r + dr[dir-1] * now.s;
            int nc = now.c + dc[dir-1] * now.s;

            if (nr < 1 || nc < 1 || nr > R || nc > C) { // 위치, 방향 조정

                if (dir == 1 || dir == 2) { // 위 아래

                    while(nr < 1 || nr > R) {
                        dir = dir == 1 ? 2 : 1;
                        int remain = nr < 1 ? Math.abs(nr) + 1 : nr - R;
                        nr = nr < 1 ? 1 : R;
                        nr += remain * dr[dir-1];
                    }
                }
                else { // 오,왼
                    while (nc < 1 || nc > C) {
                        dir = dir == 3 ? 4 : 3;
                        int remain = nc < 1 ? Math.abs(nc) + 1 : nc - C;
                        nc = nc < 1 ? 1 : C;
                        nc += remain * dc[dir-1];
                    }
                }
            }

            map[nr][nc]++;
            now.r = nr;
            now.c = nc;
            now.dir = dir;

            sharks.addLast(now);
        }

    }

    public static void remove(){

        // 중복이 발생한 위치 찾기
        ArrayList<int[]> multitude = new ArrayList<>();
        for (int i=1;i<=R;i++) {
            for(int j=1;j<=C;j++) {
                if (map[i][j] >= 2) {
                    int[] loc = {i,j};
                    multitude.add(loc);
                }
            }
        }

        // 중복 발생한 위치에서 상어 크기의 최댓값 찾기
        int[] maxSize = new int[multitude.size()];
        int sharkCnt = sharks.size();
        for (int i=0;i<multitude.size();i++) {

            int targetR = multitude.get(i)[0];
            int targetC = multitude.get(i)[1];

            for (int s=0;s<sharkCnt;s++) {
                Shark shark = sharks.pollFirst();
                if (shark.r == targetR && shark.c == targetC) {
                    maxSize[i] = Math.max(maxSize[i], shark.size);
                }
                sharks.addLast(shark);
            }
        }

        // 중복이 발생한 위치에서 크기가 가장 큰 상어 제외 없애기
        for (int i=0;i<multitude.size();i++) {

            int targetR = multitude.get(i)[0];
            int targetC = multitude.get(i)[1];
            int cnt = sharks.size();

            for (int s=0;s<cnt;s++) {
                Shark shark = sharks.pollFirst();

                if (shark.r == targetR && shark.c == targetC && maxSize[i] != shark.size) {
                    map[targetR][targetC]--;
                    continue;
                }

                sharks.addLast(shark);
            }
        }
    }
}
