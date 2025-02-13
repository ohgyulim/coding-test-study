package graph_search.level3;

import java.util.*;
import java.io.*;

public class BOJ_중량제한 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i=1; i<=N; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new int[] {B, C});
            graph.get(B).add(new int[] {A, C});
        }
        st = new StringTokenizer(br.readLine());
        int islandA = Integer.parseInt(st.nextToken());
        int islandB = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int[] visited = new int[N+1];
        visited[islandA] = 0;
        for (int[] node : graph.get(islandA)) {
            queue.offer(node);
        }

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            if (node[1] < visited[node[0]]) {
                continue;
            }
            for (int[] newNode : graph.get(node[0])) {
                if (visited[newNode[0]] < Math.max(node[1], newNode[1])) {
                    queue.offer(new int[] {newNode[0],Math.max(node[1], newNode[1]) });
                    visited[newNode[0]] = Math.max(node[1], newNode[1]);
                }
            }
        }
        System.out.println(visited[islandB]);
    }
}
