package prefix_sum.level3;
// 누적합 (열 기준)
//  (  x1,  y1 (value만큼 더함)) ( x1,   y2 ) ( x1 ,   y2 + 1 (-value만큼 더함))
//  (  x2,  y1 ) ( x2,   y2 ) ( x2,    y2 + 1)
//  ( x2+1, y1 (-value만큼 더함))  ( x2+1, y2 ) ( x2+1 , y2 + 1 (value만큼 더함))

class PGS_파괴되지_않은_건물 {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int rows = board.length;
        int cols = board[0].length;

        int[][] val = new int[rows][cols];
        for (int i=0;i<skill.length;i++) {

            int type = skill[i][0];
            int x1 = skill[i][1];
            int y1 = skill[i][2];
            int x2 = skill[i][3];
            int y2 = skill[i][4];
            int degree = skill[i][5];

            if (type == 1) {
                degree *= -1;
            }

            val[x1][y1] += degree;

            if (x2+1 < rows && y2+1 < cols) {
                val[x2+1][y2+1] += degree;
            }
            if (x2+1 < rows) {
                val[x2+1][y1] -= degree;
            }

            if (y2+1 < cols) {
                val[x1][y2+1] -= degree;
            }
        }

        for (int i=0;i<rows;i++) { // 열 구간합
            for (int j=1;j<cols;j++) {
                val[i][j] += val[i][j-1];
            }
        }

        for (int i=0;i<cols;i++) { // 행 구간합
            for (int j=1;j<rows;j++) {
                val[j][i] += val[j-1][i];
            }
        }

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                board[i][j] += val[i][j];
                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }


        return answer;
    }
}