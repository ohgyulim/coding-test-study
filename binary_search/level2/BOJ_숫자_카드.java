package binary_search.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// binary search라는데.. 어떻게 이분탐색으로 푸는 건지 모르겠음..
// 아래 코드는 이분 탐색 코드 아님
public class BOJ_숫자_카드 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int cardNum = Integer.parseInt(st.nextToken());
			set.add(cardNum);
			if (set.size() > N) {
				set.remove(cardNum);
				sb.append(0).append(" ");
			}
			else sb.append(1).append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.print(sb);
	}
}
