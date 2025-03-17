package simulation.level3;

import java.util.*;

class PGS_기둥과보설치 {
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};


        int[][][] board = new int[2][n+1][n+1]; // board[0]=보, board[1] = 기둥,
        for (int i=0; i<=n; i++) {
            Arrays.fill(board[0][i], -1);
            Arrays.fill(board[1][i], -1);
        }

        int cnt = 0;
        for (int[] build : build_frame) {
            int x = build[0]; // 가로
            int y = build[1]; // 세로
            int a = build[2]; // 0: 기둥, 1: 보
            int b = build[3]; // 0: 삭제, 1: 설치

            // a: 0일 때 (기둥일 때)
            // 설치 일때 ->
            //      y=0이거나
            //      (x,y) or (x-1,y)에 위치에 보가 있거나,
            //      (x,y-1)에 기둥이 있거나
            // 삭제 일때
            //      (x,y+1) or (x-1, y+1)에 보가 없거나(기둥에 연결되어 있는 보가 없거나)
            //      (x,y+1)에 기둥이 없거나 (내 위에 기둥이 없거나)
            //      즉 기둥위에 아무것도 없어야됌
            //      (x+1,y) and (x-1,y)에 보가 있거나

            // a: 1일 때 (보일 때)
            // 설치 일 때
            //      (x,y-1) or (x+1, y-1)에 기둥이 있거나
            //      (x-1,y) and (x+1,y)에 보가 있거나
            // 삭제 일 때
            //      (x+1,y)에 기둥이 있으면 안됨
            //
            //      (x+1,y)에 보가 있으면서 (x+1,y-1) and (x+2, y-1)에 기둥이 없으면 안됨,
            //      (x-1,y)에 보가 잇으면서 (x,y-1) and (x-1,y-1)에 기둥이 없으면 안됨
            if (a == 0 && b == 1) {
                if (y == 0 || board[0][y][x] == 1 || (x > 0 && board[0][y][x-1] == 1) || board[1][y-1][x] == 0) {
                    board[1][y][x] = 0;
                    cnt ++;
                }
            } else if (a == 0 && b == 0) {
                if ((y < n && board[0][y+1][x] == -1 && board[1][y+1][x] == -1) ||
                        (y < n &&  x > 0 && board[0][y+1][x] == 1 && board[0][y+1][x-1] == 1 && board[0][y+1][x+1] == 1)) {
                    board[1][y][x] = -1;
                    cnt --;
                }
            } else if (a == 1 && b == 1) {
                if ((y > 0 && board[1][y-1][x] == 0) ||
                        (y > 0 && x < n && board[1][y-1][x+1] == 0) ||
                        (x < n && x > 0 && x < n && board[0][y][x+1] == 1 && board[0][y][x-1] == 1)) {
                    board[0][y][x] = 1;
                    cnt ++;
                }
            } else if (a == 1 && b == 0) {
                // if (x < n && board[1][y][x+1] != 0 &&
                //     (y > 0 && board[0][y][x+1] == 1 && (board[1][y-1][x+1] == 0 || (x < n-1 && board[1][y-1][x+2] == 0))) &&
                //     (x > 0 && y > 0 && board[0][y][x-1] == 1 && (board[1][y-1][x] == 0 || board[1][y-1][x-1] == 0))) {
                //     board[0][y][x] = -1;
                //     cnt --;
                // }
                if (board[1][y-1][x] == 0 || board[1][y-1][x+1] == 0) {
                    continue;
                }
                if (x < n-1 && board[0][y][x+1] == 1 && board[1][y-1][x+1] == -1 && board[1][y-1][x+2] == -1) {
                    continue;
                }
                if (x > 1 && board[0][y][x-1] == 1 && board[1][y-1][x-1] == -1 && board[1][y-1][x-2] == -1) continue;
                board[0][y][x] = -1;
                cnt --;
            }

        }

        answer = new int[cnt][3];
        int i=0;
        for (int c=0; c<=n; c++) {
            for (int r=0; r<=n; r++){
                if (board[1][r][c] != -1) {
                    answer[i][0] = c;
                    answer[i][1] = r;
                    answer[i][2] = 0;
                    i++;
                }

                if (board[0][r][c] != -1) {
                    answer[i][0] = c;
                    answer[i][1] = r;
                    answer[i][2] = 1;
                    i++;
                }
            }
        }


        return answer;
    }
}