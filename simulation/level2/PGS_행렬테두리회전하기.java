package simulation.level2;

class PGS_행렬테두리회전하기 {
    int[][] board;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        board = new int[rows + 1][columns + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                board[i][j] = (i - 1) * columns + j;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            answer[i] = simulation(query);
        }

        return answer;
    }

    private int simulation(int[] query) {
        int answer = Integer.MAX_VALUE;

        int prev = board[query[0]][query[1]];
        for (int i = query[1] + 1; i <= query[3]; i++) {
            answer = Math.min(answer, prev);
            int tmp = board[query[0]][i];
            board[query[0]][i] = prev;
            prev = tmp;
        }

        for (int i = query[0] + 1; i <= query[2]; i++) {
            answer = Math.min(answer, prev);
            int tmp = board[i][query[3]];
            board[i][query[3]] = prev;
            prev = tmp;
        }

        for (int i = query[3] - 1; i >= query[1]; i--) {
            answer = Math.min(answer, prev);
            int tmp = board[query[2]][i];
            board[query[2]][i] = prev;
            prev = tmp;
        }

        for (int i = query[2] - 1; i >= query[0]; i--) {
            answer = Math.min(answer, prev);
            int tmp = board[i][query[1]];
            board[i][query[1]] = prev;
            prev = tmp;
        }
        return answer;
    }
}