package greedy.level2;

import java.io.*;

public class BOJ_설탕_배달 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		while (N > 0) {
			if (N % 5 == 0) {
				answer += (N / 5);
				N = 0;
				break;
			}
			N -= 3;
			answer += 1;
		}
		if (N < 0) System.out.println(-1);
		else System.out.println(answer);
	}
}
