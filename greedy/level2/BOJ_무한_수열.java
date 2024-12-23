package greedy.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_무한_수열 {
	static Map<Long, Long> map = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		long N = Long.parseLong(st.nextToken());
		long P = Long.parseLong(st.nextToken());
		long Q = Long.parseLong(st.nextToken());
		long answer = recur(N, P, Q);
		System.out.println(answer);
	}

	public static long recur(long N, long P, long Q) {
		if (N == 0) return 1;
		if (map.containsKey(N)) return map.get(N);
		long A1 = N/P;
		long A2 = N/Q;
		map.put(N, recur(A1, P, Q) + recur(A2, P, Q));
		return map.get(N);
	}
}
