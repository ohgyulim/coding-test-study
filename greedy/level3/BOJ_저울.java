package greedy.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_저울 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int n = 0;
        int[] weights = new int[N];
        for (String num : br.readLine().split(" ")) {
            weights[n++] = Integer.parseInt(num);
        }


        // sum = 4 , next = 6
        // sum = 4 , next = 3
        // sum = n , next = n - 1이면 (1~n) + n-1 을하면 n ~ 2n - 1까지 표현가능, 근데 이전 n-1을 포함하지 않는 경우일 때 n까지 포현 가능하므로 1~2n-1까지 표현가능
        // sum = n , next = n + 2이면 (1~n) + n+2  = n+2 ~ 2n + 2까지 표현 가능, 즉 n+1은 표현 불가능
        Arrays.sort(weights);
        int sum = 0;
        for (int weight : weights) {
            if (sum + 1 < weight) {
                break;
            }
            sum += weight;
        }
        System.out.println(sum + 1);

    }
}
