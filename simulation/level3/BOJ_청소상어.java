package simulation.level3;

import java.io.*;

public class BOJ_청소상어 {

    static class Fish {
        int n;
        int dir;

        public Fish(int n, int dir) {
            this.n = n;
            this.dir = dir;
        }
    }

    static class Shark {
        int r;
        int c;
        int dir;

        public Shark(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    static int FISH_TOTAL = 16;
    static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Fish[][] board = new Fish[4][4];
        int[][] fishLocation = new int[17][2];
        for (int i = 0; i < 4; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                int n = Integer.parseInt(input[j * 2]);
                int dir = Integer.parseInt(input[j * 2 + 1]);
                board[i][j] = new Fish(n, dir);
                fishLocation[n][0] = i;
                fishLocation[n][1] = j;
            }
        }

        Fish fish = board[0][0];
        Shark shark = new Shark(0, 0, fish.dir);
        moveShark(0,0,fish.n, board, fishLocation);
        moveFish(board, fishLocation, shark) ;
        findAnswer(board, fishLocation, shark, fish.n);
        System.out.println(answer);
    }

    public static void moveFish(Fish[][] board, int[][] fishLocation, Shark shark) {
        for (int i = 1; i <= FISH_TOTAL; i++) {
            int r = fishLocation[i][0];
            int c = fishLocation[i][1];
            if (r == -1) continue;

            Fish fish = board[r][c];
            int dir = fish.dir;
            for (int j = 0; j <= 7; j++) {
                int newDir = (dir + j) == 8 ? 8 : (dir + j) % 8;
                int nr = r + dr[newDir];
                int nc = c + dc[newDir];
                if (!invalidRange(nr, nc) && !(shark.r == nr && shark.c == nc)) {
                    Fish tmpFish = board[nr][nc];
                    fish.dir = newDir;
                    board[nr][nc] = fish;
                    board[r][c] = tmpFish;
                    fishLocation[i][0] = nr;
                    fishLocation[i][1] = nc;

                    if (tmpFish != null) {
                        fishLocation[tmpFish.n][0] = r;
                        fishLocation[tmpFish.n][1] = c;
                    }
                    break;
                }
            }
        }
    }

    private static boolean invalidRange(int r, int c) {
        return r < 0 || r >= 4 || c < 0 || c >= 4;
    }

    private static void findAnswer(Fish[][] board, int[][] fishLocation, Shark shark, int eatN) {
        answer = Math.max(answer, eatN);
        for (int dis = 1; dis <= 3; dis++) {
            int nr = shark.r + dr[shark.dir] * dis;
            int nc = shark.c + dc[shark.dir] * dis;

            if (invalidRange(nr, nc)) break;
            if (board[nr][nc] == null) continue;
            Fish[][] copyBoard = getCopyBoard(board);
            int[][] copyFishLocation = getCopyFishLocation(fishLocation);

            Fish fish = copyBoard[nr][nc];
            Shark copyShark = new Shark(nr, nc, fish.dir);
            moveShark(nr, nc, fish.n, copyBoard, copyFishLocation);
            moveFish(copyBoard, copyFishLocation, copyShark);
            findAnswer(copyBoard, copyFishLocation, copyShark, eatN + fish.n);
        }
    }

    private static void moveShark(int nr, int nc, int fishNumber, Fish[][] copyBoard, int[][] copyFishLocation) {
        copyBoard[nr][nc] = null;
        copyFishLocation[fishNumber][0] = -1;
        copyFishLocation[fishNumber][1] = -1;
    }

    private static int[][] getCopyFishLocation(int[][] fishLocation) {
        int[][] copyFishLocation = new int[17][2];
        for (int i = 1; i < 17; i++) {
            copyFishLocation[i][0] = fishLocation[i][0];
            copyFishLocation[i][1] = fishLocation[i][1];
        }
        return copyFishLocation;
    }

    private static Fish[][] getCopyBoard(Fish[][] board) {
        Fish[][] copyBoard = new Fish[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == null) {
                    copyBoard[i][j] = null;
                } else {
                    copyBoard[i][j] = new Fish(board[i][j].n, board[i][j].dir);
                }
            }
        }
        return copyBoard;
    }


    private static void printBoard(Fish[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == null) {
                    System.out.println("null");
                    continue;
                }
                System.out.println(board[i][j].n + " " + board[i][j].dir);
            }
        }
    }
}