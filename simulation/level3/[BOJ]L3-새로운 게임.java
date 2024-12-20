import java.io.*;
import java.util.*;

public class Main1 {
    static class Soldier {
        int x, y, direction;

        public Soldier(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public void changeDirection() {
            // 방향 반대로 바꾸기
            if (this.direction == 1) this.direction = 2;
            else if (this.direction == 2) this.direction = 1;
            else if (this.direction == 3) this.direction = 4;
            else if (this.direction == 4) this.direction = 3;
        }
    }

    static class Block {
        int color;
        List<Soldier> soldiers = new ArrayList<>();

        public Block(int color) {
            this.color = color;
        }

        public void addSoldier(Soldier soldier) {
            soldiers.add(soldier);
        }

        public void moveSoldiersTo(Block targetBlock) {
            targetBlock.soldiers.addAll(soldiers);
            soldiers.clear();
        }

        public void reverseSoldiers() {
            Collections.reverse(soldiers);
        }

        public int getSoldierCount() {
            return soldiers.size();
        }
    }

    static int[] dx = {0, 1, -1, 0};  // 1: 상, 2: 하, 3: 좌, 4: 우
    static int[] dy = {-1, 0, 0, 1};  // 1: 상, 2: 하, 3: 좌, 4: 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Block[][] map = new Block[n][n];

        // 맵 색상 정보 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int color = Integer.parseInt(st.nextToken());
                map[i][j] = new Block(color);
            }
        }

        List<Soldier> soldiers = new ArrayList<>();

        // 병사 위치와 방향 정보 입력
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());
            Soldier soldier = new Soldier(x, y, direction);
            soldiers.add(soldier);
            map[x][y].addSoldier(soldier);
        }

        int turn = 0;
        while (turn < 1000) {
            turn++;
            boolean gameOver = false;

            for (Soldier soldier : soldiers) {
                moveSoldier(soldier, map, n);

                // 게임 종료 체크
                if (checkGameOver(map, n)) {
                    gameOver = true;
                    break;
                }
            }

            if (gameOver) {
                System.out.println(turn);
                return;
            }
        }

        System.out.println(-1);
    }

    // 병사 이동 처리
    private static void moveSoldier(Soldier soldier, Block[][] map, int n) {
        int x = soldier.x;
        int y = soldier.y;
        int direction = soldier.direction;

        int nx = x + dx[direction - 1];
        int ny = y + dy[direction - 1];

        // 맵을 넘어가거나 파란색 블록이면 방향 전환
        if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny].color == 2) {
            soldier.changeDirection();
            nx = x + dx[soldier.direction - 1];
            ny = y + dy[soldier.direction - 1];

            // 바꾼 방향으로도 맵을 벗어나거나 파란색이라
            // 여전히 그 방향으로도 이동할 수 없는 경우
            if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny].color == 2) {
                return;
            }
        }

        Block currentBlock = map[x][y];
        Block targetBlock = map[nx][ny];

        // 빨간색이면 병사의 순서를 뒤집어놓고 이동
        // 이동후 값으로 좌표 갱신
        if (targetBlock.color != 2) {

            if (targetBlock.color == 1) {
                currentBlock.reverseSoldiers();
            }
            currentBlock.moveSoldiersTo(targetBlock);
            soldier.x = nx;
            soldier.y = ny;
        }
    }

    // 종료 체크 (4이상짜리 있는지 확인_
    private static boolean checkGameOver(Block[][] map, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].getSoldierCount() >= 4) {
                    return true;
                }
            }
        }
        return false;
    }
}
