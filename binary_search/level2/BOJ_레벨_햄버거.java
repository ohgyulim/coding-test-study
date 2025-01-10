package binary_search.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_레벨_햄버거 {
	// 1. level N 부터 level 1 영역까지 쪼갠다
	// 2. level 1 영역에서의 X 위치를 찾고, level 1 영역의 X 위치에 대한 패티의 개수를 구한다
	// 2. (level N - 1 패티의 수) / 2 + 2.에서 구한 값
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long X = Long.parseLong(st.nextToken());
		long[] dp = new long[N + 1];
		dp[1] = 5;
		for (int i = 2; i <= N; i++) {
			dp[i] = (dp[i - 1] * 2) + 3;
		}

		long left = 1;
		long right = dp[N];
		// long patty = (long)(3 * Math.pow(2, N-1));

		System.out.println();
	}
}
