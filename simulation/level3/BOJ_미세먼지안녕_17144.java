package simulation.level3;

import java.util.*;
import java.io.*;


public class BOJ_미세먼지안녕_17144 {
    static int R;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][] A = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            int c = 0;
            while (c < C) {
                A[r][c] = Integer.parseInt(st.nextToken());
                c++;
            }
        }

        for (int t = 0; t < T; t++) {
            spreadDust(A);
            purifyAir(A);
        }
        printAnswer(A);

    }

    public static void spreadDust(int[][] A) {
        int[][] afterA = new int[R][C];
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (A[r][c] == -1) {
                    continue;
                }
                if (A[r][c] != 0) {
                    int a = A[r][c];
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (nr < 0 || nr >= R || nc < 0 || nc >= C || A[nr][nc] == -1) {
                            continue;
                        }
                        afterA[nr][nc] += a / 5;
                        A[r][c] -= a / 5;
                    }
                    afterA[r][c] += A[r][c];
                }
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (A[r][c] == -1) {
                    continue;
                }
                A[r][c] = afterA[r][c];
            }
        }
    }

    public static void purifyAir(int[][] A) {
        int purifier = findPurifier(A);
        purifyAirTop(A, purifier, 0);
        purifyAirBottom(A, purifier + 1, 0);
    }

    public static void purifyAirTop(int[][] A, int pr, int pc) {
        int purifier = pr;
        int r = pr - 1;
        int c = pc;

        while (A[r][c] != -1) {
            if (A[pr][pc] != -1) {
                A[pr][pc] = A[r][c];
            }
            A[r][c] = 0;

            pr = r;
            pc = c;
            if (c == 0) {
                if (r == 0) {
                    c += 1;
                } else {
                    r -= 1;
                }
            } else if (r == 0) {
                if (c == C - 1) {
                    r += 1;
                } else {
                    c += 1;
                }
            } else if (c == C - 1) {
                if (r == purifier) {
                    c -= 1;
                } else {
                    r += 1;
                }
            } else if (r == purifier) {
                if (c == 0) {
                    r -= 1;
                } else {
                    c -= 1;
                }
            }
        }
    }

    public static void purifyAirBottom(int[][] A, int pr, int pc) {
        int purifier = pr;
        int r = pr + 1;
        int c = pc;

        while (A[r][c] != -1) {
            if (A[pr][pc] != -1) {
                A[pr][pc] = A[r][c];
            }
            A[r][c] = 0;

            pr = r;
            pc = c;
            if (c == 0) {
                if (r == R - 1) {
                    c += 1;
                } else {
                    r += 1;
                }
            } else if (r == R - 1) {
                if (c == C - 1) {
                    r -= 1;
                } else {
                    c += 1;
                }
            } else if (c == C - 1) {
                if (r == purifier) {
                    c -= 1;
                } else {
                    r -= 1;
                }
            } else if (r == purifier) {
                if (c == 0) {
                    r += 1;
                } else {
                    c -= 1;
                }
            }
        }
    }

    public static int findPurifier(int[][] A) {
        for (int r = 0; r < R; r++) {
            if (A[r][0] == -1) {
                return r;
            }
        }
        return 0;
    }

    public static void printAnswer(int[][] A) {
        int answer = 0;
        for (int[] a : A) {
            for (int x : a) {
                answer += x;
            }

        }
        System.out.println(answer + 2);
    }

    public static void printA(int[][] A) {
        for (int[] a : A) {
            for (int x : a) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}

