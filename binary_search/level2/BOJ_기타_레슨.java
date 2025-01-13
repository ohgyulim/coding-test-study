package binary_search.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 정답 봄
public class BOJ_기타_레슨 {
	static int[] lectures;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		int longestTime = 0;
		int totalTime = 0;
		lectures = new int[N];
		for (int i = 0; i < N; i++) {
			lectures[i] = Integer.parseInt(st.nextToken());
			longestTime = Math.max(longestTime, lectures[i]);
			totalTime += lectures[i];
		}

		System.out.println(getAnswer(longestTime, totalTime, M, N));
	}

	public static int getAnswer(int left, int right, int count, int lectureSize) {
		while (left < right) {
			int mid = (left + right) / 2;
			if (getCount(mid, lectureSize) > count) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}

	public static int getCount(int time, int lectureSize) {
		int bluRayTime = time;
		int count = 1;
		for (int i = 0; i < lectureSize; i++) {
			if (bluRayTime < lectures[i]) {
				bluRayTime = time;
				count += 1;
			}
			bluRayTime -= lectures[i];
		}

		return count;
	}
}
