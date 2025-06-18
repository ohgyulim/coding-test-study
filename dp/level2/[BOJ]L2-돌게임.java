import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[] dp = new boolean[N+1];

        if(N == 1) System.out.println("SK");
        else if(N == 2) System.out.println("CY");
        else if(N == 3) System.out.println("SK");
        else{
            dp[1] = true;
            dp[2] = false;
            dp[3] = true;

            for(int i = 4; i <= N; i++){
                if(!dp[i-1] || !dp[i-3]){
                    dp[i] = true;
                }else{
                    dp[i] = false;
                }
            }
            System.out.println(dp[N] ? "SK" : "CY");
        }


    }
}