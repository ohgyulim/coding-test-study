package simulation.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.StringTokenizer;

class Chessmen {
	int y;
	int x;
	int dir;
	Chessmen(int x, int y, int dir) {
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
}
// 체스판과 체스맨 분리
// 빨간색 발판: 말 순서 바꾸기
// 파란색 발판: 방향 바꾸기
// 체스맨 겹치면 위로 쌓기
// 푸는 중..
public class BOJ_새로운_게임 {
	static int[] dy = {0, 0, 0, -1, 1}; // 동, 서, 북, 남
	static int[] dx = {0, 1, -1, 0, 0};
	static Deque<Chessmen>[][] matrix;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		matrix = new Deque[N + 1][N + 1];

	}
}
