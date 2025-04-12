class Solution {
    //피보나치 수열형태
    public long solution(int n) {
        long[] dp = new long[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = (dp[i-1]+dp[i-2]) % 1234567;
        }

        //%1234567 for문 밖에서 하면 범위 초과
        return dp[n];
    }
}