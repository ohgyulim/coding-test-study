package binary_search.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int curWinRate = getWinRate(Y, X);
		int answer = -1;
		int left = 1;
		int right = X;

		if (curWinRate >= 99){
			System.out.println(-1);
			return;
		}

		while (left <= right) {
			int mid = (left + right) / 2;
			int nextWinRate = getWinRate(Y + mid, X + mid);

			if (nextWinRate > curWinRate) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		System.out.println(answer);

	}

	public static int getWinRate(long y, long x) {
		return (int)(y * 100 / x);
	}
}
