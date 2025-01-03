package simulation.level2;

import java.util.*;

public class PGS_혼자_놀기의_달인 {
	public int solution(int[] cards) {
		int answer = 0;
		boolean[] visited = new boolean[cards.length + 1];
		PriorityQueue<Integer> queue = new PriorityQueue<>();

		for (int start = 1; start <= cards.length; start++) {
			if (visited[start]) continue;
			int count = 0;
			int current = cards[start - 1];
			while (!visited[current]) {
				visited[current] = true;
				current = cards[current - 1];
				count += 1;
			}

			if (queue.size() < 2) queue.add(count);
			else queue.add(Math.max(queue.poll(), count));
		}

		return queue.size() < 2 ? 0 : queue.poll() * queue.poll();
	}
}
