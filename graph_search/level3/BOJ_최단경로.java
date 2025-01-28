package graph_search.level3;

import java.util.*;
import java.io.*;

public class BOJ_최단경로 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        List<int[]>[] linked = new List[V+1];
        for (int v=1; v<=V; v++) {
            linked[v] = new ArrayList<>();
        }
        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            linked[u].add(new int[] {v, w});
        }

        int[] distance = new int[V+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[K] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        pq.offer(new int[] {K,0});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            if (distance[node[0]] < node[1]) continue;

            for (int[] nextNode : linked[node[0]]) {
                if (node[1] + nextNode[1] < distance[nextNode[0]]) {
                    pq.offer(new int[] {nextNode[0], node[1] + nextNode[1]});
                    distance[nextNode[0]] = node[1] + nextNode[1];
                }
            }
        }

        for (int i=1; i<V+1; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }
}
