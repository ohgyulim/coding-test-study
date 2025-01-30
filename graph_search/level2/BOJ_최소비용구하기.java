package graph_search.level2;

import java.io.*;
import java.util.*;

public class BOJ_최소비용구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<int[]>[] graph = new ArrayList[N+1];
        for (int n=1; n<=N; n++) {
            graph[n] = new ArrayList<>();
        }
        for (int m = 0; m < M; m++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end= Integer.parseInt(st.nextToken());
            int worth = Integer.parseInt(st.nextToken());

            graph[start].add(new int[] {end, worth});
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        pq.offer(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int number = node[0];
            int cost = node[1];

            if (cost != distance[number]) continue;

            for (int[] canGoNode : graph[number]) {
                if (cost + canGoNode[1] < distance[canGoNode[0]]) {
                    distance[canGoNode[0]] = cost + canGoNode[1];
                    pq.offer(new int[] {canGoNode[0], cost + canGoNode[1]});
                }
            }
        }
        System.out.println(distance[end]);

    }
}
