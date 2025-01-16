import java.util.*;

class PGS_양궁대회 {
    public int[] solution(int n, int[] info) {

        //[2,1,1,1,0,0,0,0,0,0,0]
        //[3,2,2,2,1,1,1,1,1,1,1]

        int[][] dp = new int[info.length][n+1];
        boolean[][] visited = new boolean[info.length+1][n+1];

        for(int i=1;i<=info.length;i++){
            for(int j=0;j<=n;j++){
                if(j>=info[i-1]){
                    if(dp[i-1][j] > dp[i-1][j-info[i-1]]+(i-1)){
                        dp[i][j] = dp[i-1][j];
                    }else{
                        dp[i][j] = dp[i - 1][j - info[i - 1]] + (i - 1);
                        visited[i][j] = true;
                    }
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int[] answer = new int[info.length];
        int index = 0;
        int t = n;
        for (int i = info.length; i > 0; i--) {
            if (visited[i][t]) {
                answer[index++]=(i-1);
                t -= info[i - 1];
            }
        }

        return answer;
    }
}