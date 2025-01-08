import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        long start = 0;
        long end = 0;

        st = new StringTokenizer(br.readLine());
        long[] trees = new long[N];
        for (int i = 0; i < N; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            end = Math.max(end, trees[i]);
        }

        long result = 0;
        while (start <= end) {
            long mid = (start + end) / 2;
            long total = 0;

            for (int i = 0; i < N; i++) {
                if (trees[i] > mid) {
                    total += trees[i] - mid;
                }
            }

            if (total >= M) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }
}