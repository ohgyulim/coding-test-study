package greedy.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ_수_묶기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> minusNums = new PriorityQueue<>();
		PriorityQueue<Integer> plusNums = new PriorityQueue<>(Collections.reverseOrder());
		int zeroCnt = 0;

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num < 0) minusNums.offer(num);
			else if (num > 0) plusNums.offer(num);
			else zeroCnt += 1;
		}

		int answer = 0;
		while (minusNums.size() > 1) {
			answer += (minusNums.poll() * minusNums.poll());
		}
		while (!minusNums.isEmpty()) {
			int num = minusNums.poll();
			if (zeroCnt > 0) {
				zeroCnt -= 1;
				continue;
			}
			answer += num;
		}

		while (plusNums.size() > 1) {
			int num1 = plusNums.poll();
			int num2 = plusNums.poll();
			answer += Math.max(num1 * num2, num1 + num2);
		}

		while (!plusNums.isEmpty()) answer += plusNums.poll();
		System.out.println(answer);
	}
}
