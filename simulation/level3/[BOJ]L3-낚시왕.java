import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] init = br.readLine().split(" ");
        int r = Integer.parseInt(init[0]);
        int c = Integer.parseInt(init[1]);
        int m = Integer.parseInt(init[2]);
        int sum = 0;

        Shark[][] sea = new Shark[r][c]; // 상어 위치
        Queue<Shark> queue = new LinkedList<>();

        // 상어 정보 입력
        for (int i = 0; i < m; i++) {
            String[] row = br.readLine().split(" ");
            int x = Integer.parseInt(row[0]) - 1;
            int y = Integer.parseInt(row[1]) - 1;
            int s = Integer.parseInt(row[2]);
            int d = Integer.parseInt(row[3]);
            int z = Integer.parseInt(row[4]);
            Shark shark = new Shark(x, y, s, d, z);
            sea[x][y] = shark;
            queue.add(shark);
        }

        // 낚시왕이 오른쪽으로 이동하며 처리
        for (int col = 0; col < c; col++) {
            // 1. 낚시 (땅에 가장 가까운 상어 제거)
            sum += catchShark(sea, col);

            // 2. 상어 이동
            moveSharks(sea, queue, r, c);
        }

        System.out.println(sum);
    }

    static int catchShark(Shark[][] sea, int col) {
        for (int row = 0; row < sea.length; row++) {
            if (sea[row][col] != null) {
                int size = sea[row][col].size;
                sea[row][col] = null; // 상어 제거
                return size; // 잡은 상어 크기 반환
            }
        }
        return 0; // 상어가 없으면 0 반환
    }

    static void moveSharks(Shark[][] sea, Queue<Shark> queue, int r, int c) {
        Shark[][] newSea = new Shark[r][c]; // 상어의 새로운 위치를 저장할 배열

        while (!queue.isEmpty()) {
            Shark shark = queue.poll();
            //낚인 상어 처리
            if (sea[shark.x][shark.y] != shark) continue;

            // 속도 최적화
            // speed를 그냥쓰면 숫자가 클경우 칸마다 반복문 1번씩 돌게됨
            // speed가 20이라면 3번 위아래로 왕복해서 현재위치로 돌아왔다고 치면
            // 실제 계산은 2만큼만 하면됨
            int speed = shark.speed;
            // 위/아래 이동
            if (shark.direction <= 2) speed %= (2 * (r - 1));
                // 왼/오 이동
            else speed %= (2 * (c - 1));

            // 상어 이동
            for (int i = 0; i < speed; i++) {
                if (shark.direction == 1 && shark.x == 0) shark.changeDirection();
                if (shark.direction == 2 && shark.x == r - 1) shark.changeDirection();
                if (shark.direction == 3 && shark.y == c - 1) shark.changeDirection();
                if (shark.direction == 4 && shark.y == 0) shark.changeDirection();
                shark.move();
            }

            // 이동 후 위치에 다른 상어가 있는 경우 크기 비교
            if (newSea[shark.x][shark.y] == null) {
                newSea[shark.x][shark.y] = shark;
            } else if (newSea[shark.x][shark.y].size < shark.size) {
                newSea[shark.x][shark.y] = shark; // 큰 상어가 작은 상어를 잡아먹음
            }
        }

        // 새로운 위치 배열로 업데이트
        for (int i = 0; i < r; i++) {
            System.arraycopy(newSea[i], 0, sea[i], 0, c);
        }

        // 큐에 남은 상어 다시 추가
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (sea[i][j] != null) queue.add(sea[i][j]);
            }
        }
    }
}

class Shark {
    int x, y, speed, direction, size;

    Shark(int x, int y, int speed, int direction, int size) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.size = size;
    }

    void changeDirection() {
        if (this.direction == 1) this.direction = 2;
        else if (this.direction == 2) this.direction = 1;
        else if (this.direction == 3) this.direction = 4;
        else if (this.direction == 4) this.direction = 3;
    }

    void move() {
        if (this.direction == 1) this.x--;
        else if (this.direction == 2) this.x++;
        else if (this.direction == 3) this.y++;
        else if (this.direction == 4) this.y--;
    }
}
