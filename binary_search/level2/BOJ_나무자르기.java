package binary_search.level2;

import java.util.*;
import java.io.*;

public class BOJ_나무자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        String[] treeInput = br.readLine().split(" ");
        int[] trees = new int[N];
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(treeInput[i]);
        }
        Arrays.sort(trees);

        int min = 0;
        int max = trees[N-1];

        while (min < max) {
            int mid = (min + max) / 2;
            long sum = 0;

            for (int tree : trees) {
                if (tree > mid) {
                    sum += tree - mid;
                }
            }
            if (sum < M) {
                max = mid;
            } else {
                min = mid + 1;
            }

        }

        System.out.println(min - 1);

    }
}
