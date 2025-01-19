package binary_search.level2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 정답 봄
public class BOJ_가장_긴_증가하는_부분_수열_2 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] seq = new int[N];
		int[] LIS = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		LIS[0] = seq[0];
		int lengthOfLIS = 1;

		for (int i = 1; i < N; i++) {
			int key = seq[i];

			if (LIS[lengthOfLIS - 1] < key) {
				lengthOfLIS += 1;
				LIS[lengthOfLIS - 1] = key;
			}
			else {
				int left = 0;
				int right = lengthOfLIS;
				while (left < right) {
					int mid = (left + right) / 2;

					if(LIS[mid] < key) {
						left = mid + 1;
					}
					else {
						right = mid;
					}
				}
				LIS[left] = key;
			}
		}

		System.out.println(lengthOfLIS);
	}
}