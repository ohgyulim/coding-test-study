package binary_search.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_좋다 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		if (N > 2) {
			Arrays.sort(arr);
			for (int i = N - 1; i >= 0; i--) {
				long curNum = arr[i];
				int right = N - 1;
				int left = 0;

				while (left < right) {
					if (left == i) {
						left++;
						continue;
					}
					if (right == i) {
						right--;
						continue;
					}

					long sum = arr[left] + arr[right];

					if (sum > curNum)
						right -= 1;
					else if (sum < curNum)
						left += 1;
					else {
						answer += 1;
						break;
					}
				}
			}
		}
		System.out.println(answer);
	}
}
