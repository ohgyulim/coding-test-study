import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        int goodCount = 0;

        for (int k = 0; k < n; k++) {
            long target = arr[k];
            int i = 0, j = n - 1;

            while (i < j) {
                if (i == k) {
                    i++;
                    continue;
                }
                if (j == k) {
                    j--;
                    continue;
                }

                long sum = arr[i] + arr[j];
                if (sum == target) {
                    goodCount++;
                    break;
                } else if (sum < target) {
                    i++;
                } else {
                    j--;
                }
            }
        }

        System.out.println(goodCount);
    }
}
