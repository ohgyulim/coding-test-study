package simulation.level2;

class PGS_혼자서_하는_틱택토 {
    char[][] newBoard;
    public int solution(String[] board) {
        int answer = -1;
        newBoard = new char[3][];
        for (int i = 0; i<3;i++){
            newBoard[i] = board[i].toCharArray();
        }

        int oCnt = 0;
        int xCnt = 0;
        for (char[] row: newBoard){
            for(char s : row){
                if (s == 'O') {
                    oCnt ++;
                } else if (s == 'X') {
                    xCnt ++;
                }
            }
        }
        if (oCnt - xCnt > 1 || oCnt < xCnt) {
            return 0;
        }

        int oCompleteCnt = checkResult('O');
        int xCompleteCnt = checkResult('X');


        if (oCompleteCnt == 2 && oCnt == 5 && xCnt == 4){
            return 1;
        }


        if (oCompleteCnt > 1 || xCompleteCnt > 1){
            return 0;
        }
        if (oCompleteCnt == 1 && oCnt == xCnt){
            return 0;
        }
        if (xCompleteCnt == 1 && oCnt > xCnt) {
            return 0;
        }

        return 1;
    }
    private int checkResult(char c){ // 문자c로 한줄이 완성되는 개수
        int cnt = 0;
        for (int i = 0; i<3; i++){ // 가로로 완성
            if (newBoard[i][0] == newBoard[i][1] && newBoard[i][0] == newBoard[i][2] && newBoard[i][0] == c){
                cnt ++;
            }
        }

        for (int i = 0; i<3; i++){ // 세로로 완성
            if (newBoard[0][i] == newBoard[1][i] && newBoard[0][i] == newBoard[2][i] && newBoard[0][i] == c){
                cnt ++;
            }
        }

        // 대각선 완성
        if (newBoard[0][0] == newBoard[1][1] && newBoard[1][1] == newBoard[2][2] && newBoard[2][2] == c){
            cnt ++;
        }
        if (newBoard[0][2] == newBoard[1][1] && newBoard[1][1] == newBoard[2][0] && newBoard[2][0] == c){
            cnt ++;
        }

        return cnt;
    }
}

// X의 개수가 O의 개수 보다 많은 경우
// O의 개수와 X의 개수 차이가 1초과인 경우
// O와 X로 이루어진 1자가 각각 여러개인 경우
// O의 1자가 있는데 O의 개수와 X의 개수가 같은 경우
// X의 1자가 있는데 O의 개수가 X의 개수가 다른 경우
// O로 1자를 완성시킨게 2개이면서 O의 개수는 5개, X의 개수는 4개인경우는 가능