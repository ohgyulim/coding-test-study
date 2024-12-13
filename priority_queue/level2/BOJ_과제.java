package priority_queue.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_과제 {
	// 1. deadline >= curDay 일 때, 과제 선택 가능
	// 2. 선택 가능한 과제 중 가장 큰 값 선택
	// 3. curDay -= 1
	// 1 ~ 3 반복
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<int[]> selectableHomeWorks = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o2[0] - o1[0]);
		PriorityQueue<int[]> scoreMaxHeap = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			selectableHomeWorks.offer(new int[]{d,w});
		}

		int curDay = N;
		while (!selectableHomeWorks.isEmpty() && curDay > 0) {
			int deadLine = selectableHomeWorks.peek()[0];
			// 과제 수행하지 못하는 날은 스킵
			if (deadLine < curDay) {
				curDay -= 1;
				continue;
			}
			// curDay(현재 날짜)에 대해 과제 수행 가능한 모든 경우 중 score가 가장 큰 경우를 구하기 위해 scoreMaxHeap에 넣기
			while (!selectableHomeWorks.isEmpty()) {
				int[] homework = selectableHomeWorks.peek();
				if (homework[0] < curDay) break;
				scoreMaxHeap.offer(selectableHomeWorks.poll());
			}
			// score에 대해 내림차순이므로, 가장 윗 값이 score Max값
			if (!scoreMaxHeap.isEmpty()) answer += scoreMaxHeap.poll()[1];
			// 과제 하나 처리했으므로 selectableHomeWorks 원상복구
			while (!scoreMaxHeap.isEmpty()) {
				selectableHomeWorks.offer(scoreMaxHeap.poll());
			}
			// 과제 처리했으니까 날짜 -1
			curDay -= 1;
		}
		System.out.println(answer);
	}
}
