package greedy.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_단어_수학 {
	static class Letter {
		char alpha;
		long weight;
		Letter(char alpha, long weight) {
			this.alpha = alpha;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] weights = new long[26];

		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			int length = word.length();
			long weight = 1;
			for (int j = length - 1; j >= 0; j--) {
				char ch = word.charAt(j);
				weights[ch - 'A'] += weight;
				weight *= 10;
			}
		}

		PriorityQueue<Letter> pq = new PriorityQueue<>((o1, o2) -> (int)(o2.weight - o1.weight));
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			if (weights[ch - 'A'] > 0) {
				pq.offer(new Letter(ch, weights[ch - 'A']));
			}
		}

		long answer = 0;
		int num = 9;
		while (!pq.isEmpty()) {
			Letter letter = pq.poll();
			answer += (letter.weight * num--);
		}
		System.out.println(answer);
	}
}
