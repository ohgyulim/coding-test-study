package simulation.level2;

class PGS_쿼드압축개수세기 {
    int[] answer = new int[2];

    public int[] solution(int[][] arr) {

        recur(arr, 0, 0, arr.length - 1, arr[0].length - 1);

        return answer;
    }

    private void recur(int[][] arr, int leftR, int leftC, int rightR, int rightC) {

        int standard = arr[leftR][leftC];

        boolean flag = true;
        for (int i = leftR; i <= rightR; i++) {
            for (int j = leftC; j <= rightC; j++) {
                if (arr[i][j] != standard) {
                    recur(arr, leftR, leftC, (leftR + rightR) / 2, (leftC + rightC) / 2);
                    recur(arr, leftR, (leftC + rightC) / 2 + 1, (leftR + rightR) / 2, rightC);
                    recur(arr, (leftR + rightR) / 2 + 1, leftC, rightR, (leftC + rightC) / 2);
                    recur(arr, (leftR + rightR) / 2 + 1, (leftC + rightC) / 2 + 1, rightR, rightC);
                    return;
                }
            }
        }

        for (int i = leftR; i <= rightR; i++) {
            for (int j = leftC; j <= rightC; j++) {
                arr[i][j] = standard;
            }
        }
        if (standard == 0) {
            answer[0]++;
        } else {
            answer[1]++;
        }

    }
}