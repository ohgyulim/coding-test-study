package dijkstra.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_최소비용_구하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int cityNum = Integer.parseInt(br.readLine());
		int busNum = Integer.parseInt(br.readLine());
		for (int i = 0; i < busNum; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int startCity = Integer.parseInt(st.nextToken());
			int endCity = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");

		int startCity = Integer.parseInt(st.nextToken());
		int endCity = Integer.parseInt(st.nextToken());
	}
}
