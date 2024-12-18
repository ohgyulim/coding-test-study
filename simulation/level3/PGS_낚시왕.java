package simulation.level3;

import java.util.*;
import java.io.*;

public class PGS_낚시왕 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Shark[][] sharks = new Shark[R + 1][C + 1];
        int fisher = 0;
        int i = 0;
        while (i++ < M) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()); // 상어의 위치 r,c
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()); // 상어의 속력 s
            int d = Integer.parseInt(st.nextToken()); // 이동방향 d (위:1, 아래:2, 오른쪽:3, 왼쪽:4)
            int z = Integer.parseInt(st.nextToken()); // 크기 z
            sharks[r][c] = new Shark(s, d, z);
        }

        int answer = 0;
        while (fisher++ < C) {
            for (int r = 1; r <= R; r++) { // 낚시
                if (sharks[r][fisher] != null) {
                    answer += sharks[r][fisher].z;
                    sharks[r][fisher] = null;
                    break;
                }
            }

            // 상어 이동
            // 임시 배열 하나 만들고 거기다가 이동한 상어 정보를 넣는다.
            // 다 하면 원래 배열에 임시 배열의 값을 복사한다;
            Shark[][] tmpSharks = new Shark[R + 1][C + 1];
            for (int r = 1; r <= R; r++) {
                for (int c = 1; c <= C; c++) {
                    if (sharks[r][c] == null) {
                        continue;
                    }
                    Shark shark = sharks[r][c];
                    int s = shark.s;
                    int d = shark.d;
                    int nr = r;
                    int nc = c;

                    if (d == 3 || d == 4) {
                        s %= 2 * C - 2; // 현재 위치 c, 범위 1~C, 다시 같은 방향의 같은 위치로 이동하는 거리: C-c + C-1 + c-1 = 2C - 2

                        while (s-- > 0) {
                            if (d == 3) { // 오른쪽
                                if (nc == C) {
                                    d = 4;
                                    nc -= 1;
                                } else {
                                    nc += 1;
                                }
                            } else { // 왼쪽
                                if (nc == 1) {
                                    d = 3;
                                    nc += 1;
                                } else {
                                    nc -= 1;
                                }
                            }
                        }
                    } else {
                        s %= 2 * R - 2;

                        while (s-- > 0) {
                            if (d == 1) { // 위쪽
                                if (nr == 1) {
                                    d = 2;
                                    nr += 1;
                                } else {
                                    nr -= 1;
                                }
                            } else {
                                if (nr == R) {
                                    d = 1;
                                    nr -= 1;
                                } else {
                                    nr += 1;
                                }
                            }
                        }
                    }
                    if (tmpSharks[nr][nc] == null || tmpSharks[nr][nc].z < shark.z) {
                        tmpSharks[nr][nc] = new Shark(shark.s, d, shark.z);
                    }
                }
            }

            for (int r = 1; r <= R; r++) {
                for (int c = 1; c <= C; c++) {
                    sharks[r][c] = tmpSharks[r][c];
                }
            }
        }
        System.out.println(answer);
    }
}

class Shark {
    int s;
    int d;
    int z;

    public Shark(int s, int d, int z) {
        this.s = s;
        this.d = d;
        this.z = z;
    }
}

// 풀이 시간: 40분
// 시간: 484ms
