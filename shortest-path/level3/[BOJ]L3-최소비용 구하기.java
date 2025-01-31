import java.io.*;
import java.util.*;

class Main {
    static final int INF = 100_000_000;
    static List<List<Node>> graph = new ArrayList<>();
    static int[] dist;

    static class Node implements Comparable<Node> {
        int city, cost;
        Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        bw.write(dist[end] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.city] < now.cost) continue;

            for (Node next : graph.get(now.city)) {
                int newCost = dist[now.city] + next.cost;
                if (newCost < dist[next.city]) {
                    dist[next.city] = newCost;
                    pq.offer(new Node(next.city, newCost));
                }
            }
        }
    }
}