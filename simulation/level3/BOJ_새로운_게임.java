import java.io.*;
import java.util.*;

class Horse {
    int x, y, dir; // 말의 위치와 방향

    public Horse(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class BOJ_새로운_게임 {
    static int N, K;
    static int[][] board;
    static List<Integer>[][] map; // 각 칸에 있는 말들의 리스트
    static Horse[] horses; // 말의 정보
    static int[] dx = {0, 0, 0, -1, 1}; // →, ←, ↑, ↓
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 체스판 크기와 말 개수 입력
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        map = new ArrayList[N][N];
        horses = new Horse[K];

        // 체스판 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new ArrayList<>();
            }
        }

        // 말 정보 입력
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            horses[i] = new Horse(x, y, dir);
            map[x][y].add(i); // 말을 해당 칸에 추가
        }

        int answer = 0;
        while (answer <= 1000) {
            answer++;
            if (play()) {
                System.out.println(answer);
                return;
            }
        }
        System.out.println(-1);
    }

    static boolean play() {
        for (int i = 0; i < K; i++) {
            Horse horse = horses[i];
            int x = horse.x, y = horse.y, dir = horse.dir;

            // 해당 말이 위치한 칸에서 그 말 이후로 쌓여 있는 말들을 가져옴
            int idx = map[x][y].indexOf(i);
            List<Integer> move = new ArrayList<>(map[x][y].subList(idx, map[x][y].size()));
            map[x][y].subList(idx, map[x][y].size()).clear();

            // 이동할 위치 계산
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 체스판 밖이거나 파란색인 경우
            if (!isIn(nx, ny) || board[nx][ny] == 2) {
                // 방향 반대로 변경
                horse.dir = reverse(dir);
                nx = x + dx[horse.dir];
                ny = y + dy[horse.dir];

                // 다시 이동할 위치가 체스판 밖이거나 파란색이면 이동하지 않음
                if (!isIn(nx, ny) || board[nx][ny] == 2) {
                    map[x][y].addAll(move);
                    continue;
                }
            }

            // 이동할 칸 처리
            if (board[nx][ny] == 0) { // 흰색
                map[nx][ny].addAll(move);
            } else if (board[nx][ny] == 1) { // 빨간색
                Collections.reverse(move);
                map[nx][ny].addAll(move);
            }

            // 말들의 위치 업데이트
            for (int horseIdx : move) {
                horses[horseIdx].x = nx;
                horses[horseIdx].y = ny;
            }

            // 한 칸에 말이 4개 이상 쌓이면 게임 종료
            if (map[nx][ny].size() >= 4) {
                return true;
            }
        }
        return false;
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static int reverse(int dir) {
        if (dir == 1) return 2; 
        if (dir == 2) return 1;
        if (dir == 3) return 4; 
        return 3; 
    }
}