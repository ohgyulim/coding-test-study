import java.io.*;
import java.util.*;

public class Main {
    static final long INF = Long.MAX_VALUE;
    static int N, M;
    static ArrayList<int[]> edges;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        dist = new long[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new int[]{A, B, C});
        }

        if (bellmanFord(1)) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                if (dist[i] == INF) {
                    System.out.println(-1);
                } else {
                    System.out.println(dist[i]);
                }
            }
        }
    }

    static boolean bellmanFord(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int i = 1; i < N; i++) {
            for (int[] edge : edges) {
                int A = edge[0], B = edge[1], C = edge[2];
                if (dist[A] != INF && dist[B] > dist[A] + C) {
                    dist[B] = dist[A] + C;
                }
            }
        }

        for (int[] edge : edges) {
            int A = edge[0], B = edge[1], C = edge[2];
            if (dist[A] != INF && dist[B] > dist[A] + C) {
                return true;
            }
        }
        return false;
    }
}