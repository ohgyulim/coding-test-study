package dp.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// dp로 풀려함 -> index 범위가 10^12로 int 범위를 넘어가서 배열로는 못만듦 -> HashMap
public class BOJ_무한_수열 {

    private static Map<Long, Long> dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        dp = new HashMap<>();
        long ans = calculate(N, P, Q);
        System.out.println(ans);

        br.close();
    }

    public static long calculate(long n, int p, int q) {
        if (n == 0) {
            return 1;
        }

        if (dp.containsKey(n)) {
            return dp.get(n);
        }

        dp.put(n , calculate(n/p, p, q) + calculate(n/q, p, q));
        return dp.get(n);
    }
}
