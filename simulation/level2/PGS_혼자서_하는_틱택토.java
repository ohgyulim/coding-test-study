// 효율
// +17

// 최악 시간
//테스트 53 〉	통과 (0.05ms, 75.6MB)
//테스트 54 〉	통과 (0.05ms, 84.7MB)

// 0인 경우
//1. x의 개수가 o보다 많은 경우
//2. o의 개수와 x개수 차이가 2이상인 경우
//3. o의 개수와 x개수 차이가 1이고 , x의 개수가 4개 이상일 때 (3개 4개, 4개 5개) -> x가 틱택토를 완성한 경우
//4. o의 개수와 x개수가 같고 , x의 개수가 3개 이상일 때(3개 3개, 4개 4개) -> o가 틱택토를 완성한 경우

//o가 더 많은 경우
// X O 결과
// 0 1 1
// 0 2 0 -> 차이가 2이상
// 1 2 1
// 0 3 0 -> 차이가 2이상
// 1 3 0 -> 차이가 2이상
// 2 3 1
// 3 4 ?
// 4 5 ?

//같은 경우
// X O 결과
// 0 0 1
// 0 1 1
// 2 2 1
// 3 3 ?
// 4 4 ?


class PGS_혼자서_하는_틱택토 {
    public int solution(String[] board) {
        int answer = -1;

        //o와 x의 개수 세기
        int[] count = calCount(board);

        //개수로 조건 체크
        int check = checkCount(count);

        //3번, 4번 조건일 경우 조건 다시 체크
        if(check==-1){
            check = validate(count, board);
        }

        answer = check;

        return answer;
    }

    //o와 x개수 세기
    private int[] calCount(String[] board){
        int[] count = new int[2];
        int o = 0;
        int x = 0;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i].charAt(j)=='O') o++;
                else if(board[i].charAt(j)=='X') x++;
            }
        }
        count[0] = o;
        count[1] = x;

        return count;
    }

    // 개수 체크하기. -1인 경우 validate를 거쳐야함.
    private int checkCount(int[] count){
        if(count[0] < count[1]) return 0;
        if(count[0]-count[1] >= 2) return 0;
        if(count[0] > 3 && count[0] > count[1]) return -1;
        if(count[0] >= 3 && count[0]==count[1]) return -1;
        return 1;
    }

    //개수만으로 판단 안될 때 검증
    private int validate(int[] count, String[] board){

        //4. o의 개수와 x개수가 같고 , x의 개수가 3개 이상일 때, -> o가 틱택토를 완성한 경우
        if(count[0] >= 3 && count[0]==count[1]){
            //같은 행인지
            for(int i=0;i<3;i++){
                if(board[i].equals("OOO")){
                    return 0;
                }
            }
            //같은 열인지
            for(int j=0;j<3;j++){
                if(board[0].charAt(j)=='O' &&
                        board[1].charAt(j)=='O' &&
                        board[2].charAt(j)=='O'){
                    return 0;
                }
            }
            //대각선인지
            if(board[0].charAt(0)=='O' &&
                    board[1].charAt(1)=='O' &&
                    board[2].charAt(2)=='O'){
                return 0;
            }
            if(board[0].charAt(2)=='O' &&
                    board[1].charAt(1)=='O' &&
                    board[2].charAt(0)=='O'){
                return 0;
            }

        }else{ //3. o의 개수와 x개수 차이가 1이고 , x의 개수가 4개 이상일 때, -> x가 틱택토를 완성한 경우

            //같은 행인지
            for(int i=0;i<3;i++){
                if(board[i].equals("XXX")){
                    return 0;
                }
            }
            //같은 열인지
            for(int j=0;j<3;j++){
                if(board[0].charAt(j)=='X' &&
                        board[1].charAt(j)=='X' &&
                        board[2].charAt(j)=='X'){
                    return 0;
                }
            }
            //대각선인지
            if(board[0].charAt(0)=='X' &&
                    board[1].charAt(1)=='X' &&
                    board[2].charAt(2)=='X'){
                return 0;
            }
            if(board[0].charAt(2)=='X' &&
                    board[1].charAt(1)=='X' &&
                    board[2].charAt(0)=='X'){
                return 0;
            }

        }

        return 1;
    }
}