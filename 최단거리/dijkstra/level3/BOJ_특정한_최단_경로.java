package 최단거리.dijkstra.level3;

import java.io.*;
import java.util.*;

public class BOJ_특정한_최단_경로 {
	static class Edge {
		int to;
		long weight;
		Edge(int to, long weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	static ArrayList<Edge>[] graph;
	static int N, E, v1, v2;
	static final long INF = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[from].add(new Edge(to, weight));
			graph[to].add(new Edge(from, weight));
		}

		st = new StringTokenizer(br.readLine(), " ");
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		long[] from1To = dijkstra(1);
		long[] fromV1To = dijkstra(v1);
		long[] fromV2To = dijkstra(v2);

		long path1 = INF;
		long path2 = INF;

		// path1: 1 -> v1 -> v2 -> n
		// path2: 1 -> v2 -> v1 -> n
		if (from1To[v1] != INF && fromV1To[v2] != INF && fromV2To[N] != INF) path1 = from1To[v1] + fromV1To[v2] + fromV2To[N];
		if (from1To[v2] != INF && fromV2To[v1] != INF && fromV1To[N] != INF) path2 = from1To[v2] + fromV2To[v1] + fromV1To[N];

		long answer = path1 == INF && path2 == INF ? -1 : Math.min(path1, path2);
		System.out.println(answer);

	}

	public static long[] dijkstra(int start) {
		long[] dist = new long[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.weight, o2.weight));
		pq.offer(new Edge(start, 0));

		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int number = edge.to;
			long weight = edge.weight;

			if (weight > dist[number]) continue;
			for (Edge next : graph[number]) {
				int nextNumber = next.to;
				long nextWeight = next.weight;

				if (weight + nextWeight < dist[nextNumber]) {
					dist[nextNumber] = weight + nextWeight;
					pq.offer(new Edge(nextNumber, dist[nextNumber]));
				}
			}
		}

		return dist;
	}
}
