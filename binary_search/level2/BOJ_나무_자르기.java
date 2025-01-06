package binary_search.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_나무_자르기 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		int start = 0;
		int end = arr[N-1];

		while (start < end) {

			int mid = (start + end) / 2;
			long sum = 0;


			for (int length : arr) {
				if (length > mid) {
					sum += (length - mid);
				}
			}
			if (sum < M) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		System.out.println(start - 1);
	}
}
