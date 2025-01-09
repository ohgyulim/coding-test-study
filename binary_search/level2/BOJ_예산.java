package binary_search.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_예산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] requestInput = br.readLine().split(" ");
        int[] request = new int[N];
        int i = 0;
        for (String requestString : requestInput) {
            request[i++] = Integer.parseInt(requestString);
        }
        Arrays.sort(request);
        int M = Integer.parseInt(br.readLine());

        int left = 0;
        int right = request[N - 1];
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            long sum = 0;
            for (int req: request) {
                sum += Math.min(req, mid);
            }

            if (sum > M) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }
}
