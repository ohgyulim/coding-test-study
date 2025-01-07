package binary_search.level2;


import java.util.*;
import java.io.*;

public class BOJ_랜선_자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] lan = new int[k];
        long max = 0;
        for (int i=0;i<k;i++){
            lan[i] = Integer.parseInt(br.readLine());
            if (lan[i] > max) {
                max = lan[i];
            }
        }

        max++;

        long min = 0;
        long mid = 0;
        while (min < max) {
            mid = (min + max) / 2;

            int cnt = 0;
            for (int i=0;i<k;i++) {
                cnt += lan[i] / mid;
            }

            if (cnt < n) {
                max = mid;
            }
            else {
                min = mid + 1;
            }
        }

        System.out.println(min-1);

    }
}