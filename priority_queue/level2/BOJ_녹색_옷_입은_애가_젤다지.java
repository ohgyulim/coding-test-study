package priority_queue.level2;

import java.io.*;
import java.util.*;

public class BOJ_녹색_옷_입은_애가_젤다지 {
    private static class Node implements Comparable<Node>{
        int r, c, cost;
        public Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return cost - n.cost;
        }
    }

    private static int[][] map;
    private static int N;
    private static final int[] dr = {0, 0, -1, 1};
    private static final int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test = 1;
        while((N = Integer.parseInt(br.readLine())) != 0) {
            map = new int[N][N];
            for (int i=0;i<N;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0;j<N;j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write("Problem "+test+": " + findMinCost() + "\n");
            test++;
        }

        bw.flush();

        br.close();
        bw.close();
    }

    public static int findMinCost() {
        int totalCost = 0;
        int[][] cost = new int[N][N];
        for (int i=0;i<N;i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        cost[0][0] = map[0][0];
        pq.add(new Node(0, 0, cost[0][0]));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.r == N - 1 && curr.c == N - 1) {
                break;
            }

            for (int i=0;i<4;i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }

                if (cost[nr][nc] > curr.cost + map[nr][nc]) {
                    cost[nr][nc] = curr.cost + map[nr][nc];
                    pq.add(new Node(nr, nc, cost[nr][nc]));
                }
            }
        }

        totalCost = cost[N - 1][N - 1];
        return totalCost;
    }
}
