package greedy.level2;

import java.io.*;
import java.util.*;

public class BOJ_카드_정렬하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		if (N == 1) {
			System.out.println(0);
			return;
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}

		int answer = 0;
		while (pq.size() > 1) {
			int sum = pq.poll() + pq.poll();
			pq.offer(sum);
			answer += sum;
		}

		System.out.println(answer);
	}
}
