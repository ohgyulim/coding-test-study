package greedy.level2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_설탕배달 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        if (N >= 3) {
            dp[3] = 1;
        }

        if (N >= 5) {
            dp[5] = 1;
        }

        for (int i=6;i<=N;i++){
            if (i % 5 == 0){
                dp[i] = dp[i - 5] + 1;
            }
            else if (i % 3 == 0){
                dp[i] = dp[i - 3] + 1;
            }
            else {
                if (dp[i-3] != 0 && dp[i-5] != 0){
                    dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1; // ex) 8,
                }
            }
        }

        if (dp[N] == 0){
            bw.write("-1");
        }
        else {
            bw.write(dp[N]+"");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
