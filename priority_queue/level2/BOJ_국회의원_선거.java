package priority_queue.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ_국회의원_선거 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		int dasom = Integer.parseInt(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			maxHeap.add(Integer.parseInt(br.readLine()));
		}
		while (!maxHeap.isEmpty() && dasom <= maxHeap.peek()) {
			maxHeap.add(maxHeap.poll() - 1);
			dasom += 1;
			answer += 1;
		}
		System.out.println(answer);
	}
}
