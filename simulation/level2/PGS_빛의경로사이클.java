package simulation.level2;

import java.util.*;

class PGS_빛의경로사이클 {
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    // dir은 0~3까지 좌하우상 방향
    boolean[][][] visited; // visited[r][c][dir]: r행, c열에서 dir방향으로 나간적이 있는지
    char[][] board;

    public int[] solution(String[] grid) {
        int[] answer = {};

        board = new char[grid.length][grid[0].length()];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                board[i][j] = grid[i].charAt(j);
            }
        }
        visited = new boolean[board.length][board[0].length][4];
        List<Integer> answerList = new ArrayList<>();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                for (int dir = 0; dir < 4; dir++) {
                    if (!visited[r][c][dir]) {
                        answerList.add(simulation(r, c, dir));
                    }
                }
            }
        }
        Collections.sort(answerList);
        answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    private int simulation(int r, int c, int dir) {
        int answer = 0;
        while (!visited[r][c][dir]) {
            answer++;
            visited[r][c][dir] = true;

            r = proceedRow(r, dir);
            c = proceedCol(c, dir);
            if (board[r][c] == 'L') {
                dir = turnLeft(dir);
            } else if (board[r][c] == 'R') {
                dir = turnRight(dir);
            }
        }
        return answer;
    }

    private int turnLeft(int dir) {
        return (dir + 1) % 4;
    }

    private int turnRight(int dir) {
        return (dir + 3) % 4;
    }

    private int proceedRow(int r, int dir) {
        r = (r + dr[dir]) % board.length;
        if (r < 0) {
            r = board.length - 1;
        }
        return r;
    }

    private int proceedCol(int c, int dir) {
        c = (c + dc[dir]) % board[0].length;
        if (c < 0) {
            c = board[0].length - 1;
        }
        return c;
    }
}