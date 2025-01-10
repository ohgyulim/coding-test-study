import java.util.*;

public class 카운트_다운 {
    public int[] solution(int target) {
        // dp[점수][0] : 점수의 최소로 던진 횟수
        // dp[점수][1] : 점수의 싱글/불 맞춘 횟수
        int[][] dp = new int[target+1][2];

        for(int i=1; i<=target; i++){
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = 0;
        }

        for(int i=1; i<=target; i++){
            for(int j=1; j<=20; j++){
                // 싱글을 쏜 경우
                if(i-j >= 0){
                    //더 적게 쏜 걸로 갱신
                    if(dp[i][0] > dp[i-j][0]+1){
                        dp[i][0] = dp[i-j][0]+1;
                        dp[i][1] = dp[i-j][1]+1;

                    }
                    //같은 개수면 더 싱글/불로 갱신
                    if(dp[i][0] == dp[i-j][0]+1){
                        dp[i][1] = Math.max(dp[i][1],dp[i-j][1]+1);
                    }

                }
                // 더블 일 경우, 더 적게 쏜 경우만 갱신
                if(i-(j*2)>=0){
                    if(dp[i][0] > dp[i-j*2][0]+1){
                        dp[i][0] = dp[i-j*2][0]+1;
                        dp[i][1] = dp[i-j*2][1];
                    }

                }
                // 트리플 일 경우, 더 적게 쏜 경우만 갱신
                if(i-(j*3)>=0){
                    if(dp[i][0] > dp[i-j*3][0]+1){
                        dp[i][0] = dp[i-j*3][0]+1;
                        dp[i][1] = dp[i-j*3][1];
                    }
                }
            }
            // 불을 쏘는 경우 (더 적게 쏜 경우로 갱신)
            if(i-50 >= 0){
                if(dp[i][0] > dp[i-50][0]+1){
                    dp[i][0] = dp[i-50][0]+1;
                    dp[i][1] = dp[i-50][1]+1;
                }
                // 같은 다트 수면, 불/싱글을 더 많이 쏜 경우로 갱신
                else if(dp[i][0] == dp[i-50][0]+1){
                    dp[i][1] = Math.max(dp[i][1], dp[i-50][1]+1);
                }
            }
        }
        return new int[]{dp[target][0],dp[target][1]};
    }
}
