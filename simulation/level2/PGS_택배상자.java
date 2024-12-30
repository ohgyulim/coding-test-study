package simulation.level2;

import java.util.*;

public class PGS_택배상자 {
	public int solution(int[] order) {
		int answer = 0;
		Queue<Integer> mainContainer = new LinkedList<>();
		Stack<Integer> subContainer = new Stack<>();

		for (int i = 1; i <= order.length; i++) {
			mainContainer.offer(i);
		}

		for (int o : order) {
			while (!mainContainer.isEmpty() && mainContainer.peek() < o) {
				subContainer.push(mainContainer.poll());
			}

			if (!mainContainer.isEmpty() && mainContainer.peek() == o) {
				mainContainer.poll();
				answer += 1;
			}
			else if (!subContainer.isEmpty() && subContainer.peek() == o) {
				subContainer.pop();
				answer += 1;
			}
			else {
				break;
			}
		}

		return answer;
	}
}
