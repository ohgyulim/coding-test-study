class Solution {
    //2차원 배열로 표현했을때
    //store[e][0]은 e까지의 숫자들 중 약수의 갯수가 가장 많은 것들 중 가장 작은값
    //store[e][0]은 그때의 약수의 갯수
    private final int MAX_SIZE = 5000000;
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];

        int[] divisor = getDivisor(e);
        int[][] store = getMaxNumStore(e, divisor);

        for (int i=0; i<starts.length; i++) {
            answer[i] = store[starts[i]][1];
        }

        return answer;
    }

    private int[][] getMaxNumStore(int e, int[] divisor) {
        int[][] store = new int[e+1][2];

        store[e][0] = divisor[e];
        store[e][1] = e;
        for (int i=e-1; i>=1; i--) {
            if (divisor[i] >= store[i+1][0]) {
                store[i][0] = divisor[i];
                store[i][1] = i;
            } else {
                store[i][0] = store[i+1][0];
                store[i][1] = store[i+1][1];
            }
        }

        return store;
    }

    private int[] getDivisor(int e) {
        int[] divisor = new int[e+1];

        for (int i=1; i<=e; i++) {
            for (int j=1; j<=e/i; j++) {
                divisor[i*j] += 1;
            }
        }

        return divisor;
    }
}