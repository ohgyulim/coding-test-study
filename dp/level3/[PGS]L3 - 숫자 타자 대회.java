import java.util.*;

class Solution {
    //[출발숫자][목적숫자]까지의 가중치를 기록한 배열
    public int[][] cost = {
            { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
            { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
            { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
            { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
            { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
            { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
            { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
            { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
            { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
            { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };

    //dp[ind][left][right]는
    //ind번째 숫자를 눌러야할때 left와 right가 해당 값일 때 최소 비용
    public int[][][] dp;
    public String arr;
    //숫자 문자열의 길이
    public int len;

    public int solve(int ind, int L, int R) {
        //종료 조건
        if (ind == len) {
            return 0;
        }
        if (dp[ind][L][R] != -1) return dp[ind][L][R];

        int num = arr.charAt(ind) - '0';
        int result = Integer.MAX_VALUE;

        //왼쪽 손가락으로 움직이기
        //타겟 숫자가 현재 오른손가락의 위치와 다를때 (오른손으로 반드시 누르지 않아도 될 때)
        //움직이는 비용 cost[left][num] - left엄지에서 num까지의 가중치값
        //양손가락 이동 비용중 작은값을 dp배열에 저장
        if (num != R) result = Math.min(solve(ind+1, num, R) + cost[L][num], result);

        //오른 손가락으로 움직이기
        //왼손과 동일
        if (num != L) result = Math.min(solve(ind+1, L, num) + cost[R][num], result);


        return dp[ind][L][R] = result;
    }

    public int solution(String numbers) {
        arr = numbers;
        len = numbers.length();
        //초기화
        dp = new int[len + 1][10][10];
        for (int i = 0; i < len + 1; i++) {
            for (int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], -1);
        }
        return solve(0, 4, 6);
    }
}
