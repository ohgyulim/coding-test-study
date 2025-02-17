package priority_queue.level2;

import java.io.*;
import java.util.*;

public class BOJ_중량제한 {
    private static class Island implements Comparable<Island>{
        int num, weight;
        public Island(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Island l) {
            return l.weight - weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Island>[] edges = new ArrayList[N + 1];
        for(int i=1;i<=N;i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int weight = Integer.parseInt(st.nextToken());

            edges[u].add(new Island(v, weight));
            edges[v].add(new Island(u, weight));
        }

        st = new StringTokenizer(br.readLine());
        int[] factory = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        int[] weights = new int[N+1];
        PriorityQueue<Island> pq = new PriorityQueue<>();
        pq.add(new Island(factory[0], Integer.MAX_VALUE));

        while(!pq.isEmpty()) {
            Island curr = pq.poll();
            for (Island l : edges[curr.num]) {
                int weight = Math.min(l.weight, curr.weight);
                if (weights[l.num] < weight) {
                    weights[l.num] = weight;
                    pq.add(new Island(l.num, weight));
                }
            }
        }


        System.out.println(weights[factory[1]]);
    }
}
