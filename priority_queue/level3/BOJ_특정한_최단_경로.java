package priority_queue.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_특정한_최단_경로 {
    private static class Node implements Comparable<Node>{
        int num, weight;
        public Node (int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return weight - n.weight;
        }
    }

    private static List<Node>[] edges;
    private static final int INF = 200_000_001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N+1];
        for (int i=1;i<=N;i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[u].add(new Node(v, weight));
            edges[v].add(new Node(u, weight));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1 - A - B - N
        // 1 - B - A - N
        int[] startFromOne = findMinPath(1, N);
        int[] startFromV1 = findMinPath(v1, N);
        int[] startFromV2 = findMinPath(v2, N);

        // INF 설정 잘해야할듯. (int 범위 안넘어가게)
        int pathStartToV1 = startFromOne[v1] + startFromV1[v2] + startFromV2[N];
        int pathStartToV2 = startFromOne[v2] + startFromV2[v1] + startFromV1[N];

        int answer = Math.min(pathStartToV1, pathStartToV2);
        if (answer >= INF) {
            answer = -1;
        }

        System.out.println(answer);
    }

    public static int[] findMinPath(int start, int N) {

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(start);

        while (!pq.isEmpty()) {
            int now = pq.poll();
            for (Node node : edges[now]) {
                if (dist[node.num] > dist[now] + node.weight) {
                    dist[node.num] = dist[now] + node.weight;
                    pq.add(node.num);
                }
            }
        }

        return dist;
    }

}
