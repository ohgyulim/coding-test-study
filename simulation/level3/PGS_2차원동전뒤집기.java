package simulation.level3;

public class PGS_2차원동전뒤집기 {

    public int solution(int[][] beginning, int[][] target) {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        if (beginning[0][0] == target[0][0]) {
            int[][] beginningCopy = new int[beginning.length][beginning[0].length];
            for (int i = 0; i < beginning.length; i++) {
                for (int j = 0; j < beginning[0].length; j++) {
                    beginningCopy[i][j] = beginning[i][j];
                }
            }
            reverseCol(0, beginningCopy);
            reverseRow(0, beginningCopy);
            a = solv(beginningCopy, target) + 2;
        }
        b = solv(beginning, target);
        return Math.min(a, b);
    }


    private int solv(int[][] beginning, int[][] target) {
        int answer = 0;

        int firstRowDiff = 0;
        int firstColDiff = 0;

        for (int c = 0; c < beginning[0].length; c++) {
            if (beginning[0][c] != target[0][c]) {
                firstRowDiff++;
            }
        }

        for (int r = 0; r < beginning.length; r++) {
            if (beginning[r][0] != target[r][0]) {
                firstColDiff++;
            }
        }

        if (firstRowDiff >= firstColDiff) {
            for (int r = 0; r < beginning.length; r++) {
                if (beginning[r][0] != target[r][0]) {
                    reverseRow(r, beginning);
                    answer++;
                }
            }

            for (int c = 0; c < beginning[0].length; c++) {
                if (beginning[0][c] != target[0][c]) {
                    reverseCol(c, beginning);
                    answer++;
                }
            }
        } else {
            for (int c = 0; c < beginning[0].length; c++) {
                if (beginning[0][c] != target[0][c]) {
                    reverseCol(c, beginning);
                    answer++;
                }
            }

            for (int r = 0; r < beginning.length; r++) {
                if (beginning[r][0] != target[r][0]) {
                    reverseRow(r, beginning);
                    answer++;
                }
            }
        }

        return isTarget(beginning, target) ? answer : -1;
    }

    private void reverseCol(int col, int[][] board) {
        for (int r = 0; r < board.length; r++) {
            board[r][col] = (board[r][col] + 1) % 2;
        }
    }

    private void reverseRow(int row, int[][] board) {
        for (int c = 0; c < board[0].length; c++) {
            board[row][c] = (board[row][c] + 1) % 2;
        }
    }

    private boolean isTarget(int[][] board, int[][] target) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] != target[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }
}
