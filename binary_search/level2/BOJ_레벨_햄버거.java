package binary_search.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1. level N 부터 level 1 영역까지 쪼갠다
// 2. level 1 영역에서의 X 위치를 찾고, level 1 영역의 X 위치에 대한 패티의 개수를 구한다
// 3. level 1 영역의 패티의 수 부터 return하면서 더한다
public class BOJ_레벨_햄버거 {
	static long[] layers;
	static long[] patties;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long X = Long.parseLong(st.nextToken());

		layers = new long[N + 1];
		patties = new long[N + 1];
		layers[0] = 1;
		patties[0] = 1;

		for (int i = 1; i <= N; i++) {
			layers[i] = layers[i - 1] * 2 + 3;
			patties[i] = patties[i - 1] * 2 + 1;
		}

		System.out.println(recur(N, X));
	}

	public static long recur(int level, long x) {
		if (level == 0) {
			return 1;
		}

		long half = layers[level - 1]; // 길이

		if (x == 1) {
			return 0;
		} else if (x <= half + 1) { // mid 보다 왼쪽에 있으면
			return recur(level - 1, x - 1); // 맨 앞 한 칸 뺌
		} else if (x == half + 2) { // half + (맨 앞 B + 맨 뒤 B)를 하면 mid값임
			return patties[level - 1] + 1; // mid가 패티이기 때문에 + 1
		} else { // mid보다 오른쪽에 있으면
			return patties[level - 1] + 1 + recur(level - 1, x - half - 2);
		}
	}
}
