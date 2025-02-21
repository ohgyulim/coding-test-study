package graph_search.level3;

import java.util.*;
import java.io.*;

public class BOJ_타임머신 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Edge[] graph = new Edge[M];

        for (int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[m] = new Edge(A, B, C);
        }
        long[] dist = new long[N+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        for (int i=1; i<=N; i++) {
            for (int m=0; m<M; m++) {
                Edge edge = graph[m];

                if (dist[edge.v] != Long.MAX_VALUE && dist[edge.w] > dist[edge.v] + edge.cost) {
                    dist[edge.w] = dist[edge.v] + edge.cost;
                }
            }
        }

        for (int m=0; m<M; m++) {
            Edge edge = graph[m];

            if (dist[edge.v] != Long.MAX_VALUE && dist[edge.w] > dist[edge.v] + edge.cost) {
                System.out.println(-1);
                return;
            }
        }

        for (int n=2; n<=N; n++) {
            if (dist[n] == Long.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(dist[n]);
            }
        }
    }

}

class Edge {
    int v;
    int w;
    int cost;

    public Edge(int v, int w, int cost) {
        this.v = v;
        this.w = w;
        this.cost = cost;
    }
}