package binary_search.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// binary search로 풀어야 하는데 못품
public class BOJ_예산 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int total = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(st.nextToken());
			arr[i] = number;
			total += number;
		}
		Arrays.sort(arr);
		int max = arr[N - 1];
		int budget = Integer.parseInt(br.readLine());
		if (total <= budget) System.out.println(max);
		else {
			while (total > budget) {
				max -= 1;
				for (int i = N - 1; i >= 0; i--) {
					if (max >= arr[i]) break;
					total -= 1;
				}
			}
			System.out.println(max);
		}
	}
}
