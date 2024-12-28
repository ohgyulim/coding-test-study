import java.util.*;
import java.io.*;

public class BOJ_원판_돌리기 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] disks = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                disks[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] rotations = new int[T][3];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            rotations[i][0] = Integer.parseInt(st.nextToken()); // x
            rotations[i][1] = Integer.parseInt(st.nextToken()); // d
            rotations[i][2] = Integer.parseInt(st.nextToken()); // k
        }
        
        for (int[] rotation : rotations) {
            rotate(disks, rotation[0], rotation[1], rotation[2], N, M);
            if (!remove(disks, N, M)) {
                adjustNumbers(disks, N, M);
            }
        }
        
        System.out.println(cal(disks, N, M));
    }

    // 원판 회전
    private static void rotate(int[][] disks, int x, int d, int k, int N, int M) {
        for (int i = x - 1; i < N; i += x) {
            if (d == 0) { // 시계 방향
                for (int t = 0; t < k; t++) {
                    int temp = disks[i][M - 1];
                    System.arraycopy(disks[i], 0, disks[i], 1, M - 1);
                    disks[i][0] = temp;
                }
            } else { // 반시계 방향
                for (int t = 0; t < k; t++) {
                    int temp = disks[i][0];
                    System.arraycopy(disks[i], 1, disks[i], 0, M - 1);
                    disks[i][M - 1] = temp;
                }
            }
        }
    }

    // 인접한 숫자 제거
    private static boolean remove(int[][] disks, int N, int M) {
        boolean isRemoved = false;
        boolean[][] remove = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (disks[i][j] == 0) continue;
                int current = disks[i][j];

                // 같은 원판 내 인접
                if (disks[i][(j + 1) % M] == current) {
                    remove[i][j] = true;
                    remove[i][(j + 1) % M] = true;
                }
                if (disks[i][(j - 1 + M) % M] == current) {
                    remove[i][j] = true;
                    remove[i][(j - 1 + M) % M] = true;
                }

                // 다른 원판 간 인접
                if (i > 0 && disks[i - 1][j] == current) {
                    remove[i][j] = true;
                    remove[i - 1][j] = true;
                }
                if (i < N - 1 && disks[i + 1][j] == current) {
                    remove[i][j] = true;
                    remove[i + 1][j] = true;
                }
            }
        }

        // 제거 실행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (remove[i][j]) {
                    disks[i][j] = 0;
                    isRemoved = true;
                }
            }
        }

        return isRemoved;
    }

    // 평균 기반 숫자 조정
    private static void adjustNumbers(int[][] disks, int N, int M) {
        int sum = 0, count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (disks[i][j] != 0) {
                    sum += disks[i][j];
                    count++;
                }
            }
        }

        if (count == 0) return;
        double avg = (double) sum / count;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (disks[i][j] != 0) {
                    if (disks[i][j] > avg) disks[i][j]--;
                    else if (disks[i][j] < avg) disks[i][j]++;
                }
            }
        }
    }

    // 최종 합 계산
    private static int cal(int[][] disks, int N, int M) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum += disks[i][j];
            }
        }
        return sum;
    }
}