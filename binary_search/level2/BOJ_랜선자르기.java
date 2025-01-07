package binary_search.level2;

import java.io.*;
import java.util.*;

public class BOJ_랜선자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] KN = br.readLine().split(" ");

        int K = Integer.parseInt(KN[0]);
        int N = Integer.parseInt(KN[1]);
        long[] lens = new long[K];
        for (int i = 0; i < K; i++) {
            lens[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(lens);
        long min = 1;
        long max = lens[K - 1];
        long answer = 0;
        while (min <= max) {
            long mid = (min + max) / 2;
            int cnt = 0;
            for (int k = 0; k < K; k++) {
                cnt += lens[k] / mid;
            }

            if (cnt >= N) {
                answer = Math.max(answer, mid);
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        System.out.println(answer);

    }
}
