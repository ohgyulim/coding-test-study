package implementation.level2;

// 전체 1의 개수와 0의 개수를 세 놓음. (전역변수로)
// 배열의 모든 원소가 같은지 판단
// 같다면, 압축 시킴(전체 1 or 0 개수에서 감소), 작업 끝낸다. (return)
// 다르다면, 4등분해서 같아질 때까지 넘김.

class PGS_쿼드압축_후_개수_세기 {

    private static int oneCnt;
    private static int zeroCnt;
    private static int[][] temp;

    public int[] solution(int[][] arr) {

        temp = arr;
        int size = arr.length;
        for (int i=0;i<size;i++) {
            for (int j=0;j<size;j++) {
                if (arr[i][j] == 1) {
                    oneCnt++;
                }
                else {
                    zeroCnt++;
                }
            }
        }

        countOne(0,0,size-1,size-1);

        int[] answer = {zeroCnt, oneCnt};
        return answer;
    }

    public void countOne(int startR, int startC, int endR, int endC){

        if (isAllOne(startR, startC, endR, endC)) {
            int cnt = (int) Math.pow(endR - startR + 1, 2) - 1;
            oneCnt -= cnt;
            return;
        }

        if (isAllZero(startR, startC, endR, endC)) {
            int cnt = (int) Math.pow(endR - startR + 1, 2) - 1;
            zeroCnt -= cnt;
            System.out.println("zeroCnt = " + zeroCnt);
            return;
        }

        int half = (endR - startR + 1) / 2;
        if (half != 1) {
            countOne(startR,startC,startR+half-1,startC+half-1); // 1사분면
            countOne(startR,startC+half,startR+half-1,endC); // 2사분면
            countOne(startR+half,startC,endR,startC+half-1); // 3사분면
            countOne(startR+half,startC+half,endR,endC); // 4사분면
        }
    }

    public boolean isAllOne(int startR, int startC, int endR, int endC){
        for (int r=startR;r<=endR;r++) {
            for (int c=startC;c<=endC;c++) {
                if (temp[r][c] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isAllZero(int startR, int startC, int endR, int endC){
        for (int r=startR;r<=endR;r++) {
            for (int c=startC;c<=endC;c++) {
                if (temp[r][c] != 0) {
                    return false;
                }
            }
        }

        return true;
    }
}