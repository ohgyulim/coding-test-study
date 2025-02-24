package 최단거리.벨만포드;

import java.io.*;
import java.util.*;

public class BOJ_타임머신 {
	static class Edge {
		int from;
		int to;
		int weight;
		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	static int N, M;
	static int INF = Integer.MAX_VALUE;
	static Edge[] graph;
	static long[] dist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new Edge[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[i] = new Edge(from, to, weight);
		}

		dist = new long[N + 1];
		Arrays.fill(dist, INF);

		if (hasMinusCycle(1)) System.out.println(-1);
		else {
			for (int vertex = 2; vertex <= N; vertex++) {
				if (dist[vertex] == INF) System.out.println(-1);
				else System.out.println(dist[vertex]);
			}
		}
	}

	public static boolean hasMinusCycle(int start) {
		dist[start] = 0;

		for (int vertex = 1; vertex <= N; vertex++) {
			for (int edge = 1; edge <= M; edge++) {
				int from = graph[edge].from;
				int to = graph[edge].to;
				int weight = graph[edge].weight;

				if (dist[from] == INF) continue;

				if (dist[to] > dist[from] + weight) {
					dist[to] = dist[from] + weight;

					if (vertex == N)
						return true;
				}
			}
		}
		return false;
	}
}
