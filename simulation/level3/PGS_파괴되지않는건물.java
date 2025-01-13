package simulation.level3;
// 시간초과
class PGS_파괴되지않는건물 {
    public int solution(int[][] board, int[][] skills) {
        int answer = 0;

        for (int[] skill : skills) {
            calcBoard(board, skill);
        }

        answer = countNotDestroyed(board);
        return answer;
    }

    private void calcBoard(int[][] board, int[] skill) {
        int type = skill[0];
        int sr = skill[1];
        int sc = skill[2];
        int er = skill[3];
        int ec = skill[4];
        int degree = skill[5];

        for (int r=sr; r<=er; r++) {
            for (int c=sc; c<=ec; c++){
                if (type == 1) {
                    board[r][c] -= degree;
                } else {
                    board[r][c] += degree;
                }
            }
        }
    }

    private int countNotDestroyed(int[][] board) {
        int cnt = 0;
        for (int r=0; r<board.length; r++) {
            for (int c=0; c<board[0].length; c++) {
                if (board[r][c] > 0) {
                    cnt ++;
                }
            }
        }
        return cnt;
    }
}
