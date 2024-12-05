package priority_queue.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_가운데를_말해요 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i ++) {
			int n = Integer.parseInt(br.readLine());
			maxHeap.add(n);
			if (maxHeap.size() - 1 > i/2) {
				minHeap.add(maxHeap.poll());
			}
			if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
				int maxHeapValue = maxHeap.poll();
				int minHeapValue = minHeap.poll();
				maxHeap.add(minHeapValue);
				minHeap.add(maxHeapValue);
			}
			sb.append(maxHeap.peek()).append("\n");
		}
		System.out.print(sb);
	}
}
