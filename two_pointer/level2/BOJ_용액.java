package two_pointer.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class BOJ_용액 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = N - 1;
		long min = Long.MAX_VALUE;
		int leftAns = 0;
		int rightAns = 0;
		while (left < right) {
			long sum = arr[left] + arr[right];
			if (min > Math.abs(sum)) {
				min = Math.abs(sum);
				leftAns = arr[left];
				rightAns = arr[right];
			}
			if (sum < 0) left += 1;
			else if (sum > 0) right -= 1;
			else break;
		}
		System.out.println(leftAns + " " + rightAns);
	}
}
