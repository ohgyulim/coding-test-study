import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T; // 원판의 행(N), 열(M), 회전 횟수(T)
    static int[][] board; // 원판 배열
    static boolean[][] visited; // 방문 여부 체크

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        // 원판 입력
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // T번의 회전 명령 입력 및 처리
        for (int t = 0; t < T; t++) {
            //0 시계 1 반시계
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            //회전
            rotateDisks(x, d, k);

            //인접 숙자 확인, 카운팅
            if (!removeAdjacent()) {
                //평균 계산 하고 조정
                adjustByAverage();
            }
        }

        System.out.println(calculateSum());
    }

    // 원판 회전
    static void rotateDisks(int x, int d, int k) {
        // x의 배수인 원판만 회전

        //0시계 1반시꼐
        for (int i = x - 1; i < N; i += x) {
            if (d == 0) {
                rotateClockwise(i, k);
            } else {
                rotateCounterClockwise(i, k);
            }
        }
    }

    // 시계 방향
    static void rotateClockwise(int i, int k) {
        int[] temp = new int[M];
        for (int j = 0; j < M; j++) {
            temp[(j + k) % M] = board[i][j];
        }
        board[i] = temp;
    }

    // 반시계 방향
    static void rotateCounterClockwise(int i, int k) {
        int[] temp = new int[M];
        for (int j = 0; j < M; j++) {
            temp[j] = board[i][(j + k) % M];
        }
        board[i] = temp;
    }

    // 인접 숫자 제거
    static boolean removeAdjacent() {
        visited = new boolean[N][M]; // 방문 여부 초기화
        boolean hasAdjacent = false; // 인접 여부 체크

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) continue;
                if (checkAndRemove(i, j)) {
                    hasAdjacent = true;
                }
            }
        }

        return hasAdjacent;
    }

    // 인접 숫자 검사 , 제거
    static boolean checkAndRemove(int x, int y) {
        int[] dx = {0, 0, 1, -1}; // 상하좌우 이동
        int[] dy = {1, -1, 0, 0}; // 상하좌우 이동

        boolean found = false;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0], cy = curr[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = (cy + dy[i] + M) % M; // 원형 구조 처리

                if (nx < 0 || nx >= N) continue; // 범위 밖이면 무시
                if (!visited[nx][ny] && board[nx][ny] == board[x][y]) {
                    visited[nx][ny] = true;
                    board[nx][ny] = 0; // 제거
                    board[cx][cy] = 0; // 현재 위치도 제거
                    queue.add(new int[]{nx, ny});
                    found = true;
                }
            }
        }

        return found;
    }

    //평균
    static void adjustByAverage() {
        int sum = 0, count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0) {
                    sum += board[i][j];
                    count++;
                }
            }
        }

        if (count == 0) return;

        double avg = (double) sum / count;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0) {
                    if (board[i][j] > avg) board[i][j]--;
                    else if (board[i][j] < avg) board[i][j]++;
                }
            }
        }
    }

    //최;조ㅓ합
    static int calculateSum() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum += board[i][j];
            }
        }
        return sum;
    }
}
