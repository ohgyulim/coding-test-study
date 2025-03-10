package greedy.level2;

import java.util.*;
import java.io.*;

public class BOJ_보석_도둑 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] jewels = new int[N][2];
		int[] bags = new int[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			jewels[i][0] = Integer.parseInt(st.nextToken()); // 무게
			jewels[i][1] = Integer.parseInt(st.nextToken()); // 가치
		}

		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}

		// 보석은 무게 기준으로 오름차순 정렬, 무게가 같다면 가치 기준 내림차순
		Arrays.sort(jewels, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);

		// 가방은 무게 기준 오름차순 정렬
		Arrays.sort(bags);

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		long answer = 0;
		int jewelIndex = 0;

		for (int bag : bags) {
			// 현재 가방에 담을 수 있는 모든 보석을 우선순위 큐에 추가
			while (jewelIndex < N && jewels[jewelIndex][0] <= bag) {
				pq.offer(jewels[jewelIndex][1]);
				jewelIndex++;
			}

			// 가장 가치가 높은 보석을 선택
			if (!pq.isEmpty()) {
				answer += pq.poll();
			}
		}

		System.out.println(answer);
	}
}
