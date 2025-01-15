package binary_search.level2;

import java.io.*;
import java.util.*;

public class BOJ_용액 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] liquids = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = {1_000_000_001, 1_000_000_001};

        int start = 0;
        int end = N-1;

        while(start < end) {
            int curr = liquids[start] + liquids[end];
            if (Math.abs(curr) < Math.abs(answer[0] + answer[1])) {
                answer[0] = liquids[start];
                answer[1] = liquids[end];
            }

            if (curr == 0) {
                break;
            }

            if (curr < 0) {
                start++;
            }
            else {
                end--;
            }
        }

        System.out.println(answer[0]+" "+answer[1]);

    }

}