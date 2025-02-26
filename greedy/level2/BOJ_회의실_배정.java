package greedy.level2;

import java.io.*;
import java.util.*;

public class BOJ_회의실_배정 {
	static class Time {
		int start;
		int end;
		Time(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Time[] times = new Time[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			times[i] = new Time(start, end);
		}

		Arrays.sort(times, (o1, o2) -> o1.end == o2.end ? o1.start - o2.start : o1.end - o2.end);

		int answer = 0;
		int curTime = -1;
		for (int i = 0; i < N; i++) {
			Time cur = times[i];
			int start = cur.start;
			int end = cur.end;
			if (start >= curTime) {
				answer += 1;
				curTime = end;
			}
		}

		System.out.println(answer);
	}
}
