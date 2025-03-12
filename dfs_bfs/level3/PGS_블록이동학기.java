package dfs_bfs.level3;

import java.util.*;

class PGS_블록이동학기 {
    public int solution(int[][] board) {
        int answer = 0;
        int N = board.length;

        Deque<Robot> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][N][2];
        //visited[r][c][0] : 로봇의 좌측이 r,c이고 가로로 해당 지점에 방문 했는지
        //visited[r][c][1] : 로봇의 상단이 r,c이고 세로로 해당 지점에 방문 햇는지
        queue.offer(new Robot(0, 0, 0, 1, 0));
        visited[0][0][0] = true;

        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};

        while (!queue.isEmpty()) {
            Robot robot = queue.poll();
            if (robot.r2 == N - 1 && robot.c2 == N - 1) {
                answer = robot.second;
                break;
            }
            // 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nr1 = robot.r1 + dr[i];
                int nc1 = robot.c1 + dc[i];

                int nr2 = robot.r2 + dr[i];
                int nc2 = robot.c2 + dc[i];

                // 범위 체크
                if (!isValid(board, nr1, nc1, N) || !isValid(board, nr2, nc2, N)) continue;

                if (robot.isHorizon() && !visited[nr1][nc1][0]) {
                    visited[nr1][nc1][0] = true;
                    Robot newRobot = new Robot(nr1, nc1, nr2, nc2, robot.second + 1);
                    queue.offer(newRobot);
                } else if (!robot.isHorizon() && !visited[nr1][nc1][1]) {
                    visited[nr1][nc1][1] = true;
                    Robot newRobot = new Robot(nr1, nc1, nr2, nc2, robot.second + 1);
                    queue.offer(newRobot);
                }
            }

            // 회전
            if (robot.availableLeftUp(board) && !visited[robot.r1 - 1][robot.c1][1]) { // 왼쪽을 기준으로 위로 회전
                int nr1 = robot.r1 - 1;
                int nc1 = robot.c1;

                int nr2 = robot.r1;
                int nc2 = robot.c1;
                Robot newRobot = new Robot(nr1, nc1, nr2, nc2, robot.second + 1);
                queue.offer(newRobot);
                visited[nr1][nc1][1] = true;
            }

            if (robot.availableLeftDown(board) && !visited[robot.r1][robot.c1][1]) { // 왼쪽을 기준으로 아래로 회전
                int nr1 = robot.r1;
                int nc1 = robot.c1;

                int nr2 = robot.r1 + 1;
                int nc2 = robot.c1;
                Robot newRobot = new Robot(nr1, nc1, nr2, nc2, robot.second + 1);
                queue.offer(newRobot);
                visited[nr1][nc1][1] = true;
            }

            if (robot.availableRightUp(board) && !visited[robot.r2 - 1][robot.c2][1]) { // 오른쪽을 기준으로 위로 회전
                int nr1 = robot.r2 - 1;
                int nc1 = robot.c2;

                int nr2 = robot.r2;
                int nc2 = robot.c2;
                Robot newRobot = new Robot(nr1, nc1, nr2, nc2, robot.second + 1);
                queue.offer(newRobot);
                visited[nr1][nc1][1] = true;
            }

            if (robot.availableRightDown(board) && !visited[robot.r2][robot.c2][1]) { // 오른쪽을 기준으로 아래로 회전
                int nr1 = robot.r2;
                int nc1 = robot.c2;

                int nr2 = robot.r2 + 1;
                int nc2 = robot.c2;
                Robot newRobot = new Robot(nr1, nc1, nr2, nc2, robot.second + 1);
                queue.offer(newRobot);
                visited[nr1][nc1][1] = true;
            }

            if (robot.availableUpLeft(board) && !visited[robot.r1][robot.c1 - 1][0]) { // 위쪽을 기준으로 왼쪽으로 회전
                int nr1 = robot.r1;
                int nc1 = robot.c1 - 1;

                int nr2 = robot.r1;
                int nc2 = robot.c1;
                Robot newRobot = new Robot(nr1, nc1, nr2, nc2, robot.second + 1);
                queue.offer(newRobot);
                visited[nr1][nc1][0] = true;
            }

            if (robot.availableUpRight(board) && !visited[robot.r1][robot.c1][0]) { // 위쪽을 기준으로 오론쪽으로 회전
                int nr1 = robot.r1;
                int nc1 = robot.c1;

                int nr2 = robot.r1;
                int nc2 = robot.c1 + 1;
                Robot newRobot = new Robot(nr1, nc1, nr2, nc2, robot.second + 1);
                queue.offer(newRobot);
                visited[nr1][nc1][0] = true;
            }

            if (robot.availableDownLeft(board) && !visited[robot.r2][robot.c2 - 1][0]) { // 아래쪽을 기준으로 왼쪽으로 회전
                int nr1 = robot.r2;
                int nc1 = robot.c2 - 1;

                int nr2 = robot.r2;
                int nc2 = robot.c2;
                Robot newRobot = new Robot(nr1, nc1, nr2, nc2, robot.second + 1);
                queue.offer(newRobot);
                visited[nr1][nc1][0] = true;
            }

            if (robot.availableDownRight(board) && !visited[robot.r2][robot.c2][0]) { // 아래쪽을 기준으로 오른쪽으로 회전
                int nr1 = robot.r2;
                int nc1 = robot.c2;

                int nr2 = robot.r2;
                int nc2 = robot.c2 + 1;
                Robot newRobot = new Robot(nr1, nc1, nr2, nc2, robot.second + 1);
                queue.offer(newRobot);
                visited[nr1][nc1][0] = true;
            }
        }


        return answer;
    }

    public boolean isValid(int[][] board, int r, int c, int N) {
        if (r < 0 || r >= N || c < 0 || c >= N || board[r][c] == 1) return false;
        return true;
    }

    class Robot {
        int r1, c1, r2, c2; // 무조건 좌측 상단이 r1, c1
        int second = 0;

        public Robot(int r1, int c1, int r2, int c2, int second) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.second = second;
        }

        public boolean isHorizon() {
            return r1 == r2;
        }

        public boolean availableLeftUp(int[][] board) {
            if (!isHorizon()) return false;
            int r = r1;
            int c = c1;

            if (r > 0 && board[r - 1][c] == 0 && board[r - 1][c + 1] == 0) {
                return true;
            }
            return false;
        }

        public boolean availableLeftDown(int[][] board) {
            if (!isHorizon()) return false;
            int r = r1;
            int c = c1;

            if (r < board.length - 1 && board[r + 1][c] == 0 && board[r + 1][c + 1] == 0) {
                return true;
            }
            return false;
        }

        public boolean availableRightUp(int[][] board) {
            if (!isHorizon()) return false;
            int r = r2;
            int c = c2;

            if (r > 0 && board[r - 1][c] == 0 && board[r - 1][c - 1] == 0) {
                return true;
            }
            return false;
        }

        public boolean availableRightDown(int[][] board) {
            if (!isHorizon()) return false;
            int r = r2;
            int c = c2;

            if (r < board.length - 1 && board[r + 1][c] == 0 && board[r + 1][c - 1] == 0) {
                return true;
            }
            return false;
        }

        public boolean availableUpLeft(int[][] board) {
            if (isHorizon()) return false;
            int r = r1;
            int c = c1;

            if (c > 0 && board[r][c - 1] == 0 && board[r + 1][c - 1] == 0) {
                return true;
            }
            return false;
        }

        public boolean availableUpRight(int[][] board) {
            if (isHorizon()) return false;
            int r = r1;
            int c = c1;

            if (c < board.length - 1 && board[r][c + 1] == 0 && board[r + 1][c + 1] == 0) {
                return true;
            }
            return false;
        }

        public boolean availableDownLeft(int[][] board) {
            if (isHorizon()) return false;
            int r = r2;
            int c = c2;

            if (c > 0 && board[r][c - 1] == 0 && board[r - 1][c - 1] == 0) {
                return true;
            }
            return false;
        }

        public boolean availableDownRight(int[][] board) {
            if (isHorizon()) return false;
            int r = r2;
            int c = c2;

            if (c < board.length - 1 && board[r][c + 1] == 0 && board[r - 1][c + 1] == 0) {
                return true;
            }
            return false;
        }
    }
}