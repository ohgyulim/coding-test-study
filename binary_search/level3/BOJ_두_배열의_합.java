package binary_search.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// newTarget = T - A 배열의 임의의 값
// B 배열에서 이진 탐색으로 newTarget 구함
// -> 시간 초과
public class BOJ_두_배열의_합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arrA = new int[N];
		for (int i = 0; i < N; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arrA);

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		int[] arrB = new int[M];
		for (int i = 0; i < M; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arrB);


	}
}
