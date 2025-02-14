package graph_search.level3;

import java.util.*;

class PGS_모두0으로만들기 {
    public long solution(int[] b, int[][] edges) {
        long answer = 0;
        long[] a = new long[b.length];
        for (int i = 0; i < b.length; i++) {
            a[i] = (long) b[i];
        }
        // 일단 a의총합이 0이 아니면 -1
        //
        long sumA = 0;
        for (long n : a) {
            sumA += n;
        }
        if (sumA != 0) {
            return -1;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        PriorityQueue<long[]> queue = new PriorityQueue<>((o1, o2) -> Long.compare(o2[3], o1[3]));
        int[] heights = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if (graph.get(i).size() > 1 || a.length == 2) {
                int height = 0;
                heights[i] = 0;
                Deque<int[]> children = new LinkedList<>();
                children.offer(new int[]{i, -1, 0});
                while (!children.isEmpty()) {
                    int[] parent = children.poll();
                    for (int linked : graph.get(parent[0])) {
                        if (linked == parent[1]) continue;
                        heights[linked] = parent[2] + 1;
                        children.offer(new int[]{linked, parent[0], heights[linked]});
                    }
                }
                break;
            }
        }

        for (int i = 0; i < a.length; i++) {
            if (graph.get(i).size() == 1 && heights[i] != 0) {
                int next = graph.get(i).get(0);
                a[next] += a[i];
                answer += Math.abs(a[i]);
                a[i] = 0;
                queue.offer(new long[]{next, i, a[next], heights[next]});
            }
        }

        while (!queue.isEmpty()) {
            long[] node = queue.poll();
            //System.out.println(node[3]);
            if (a[(int) node[0]] != node[2]) {
                continue;
            }
            for (int next : graph.get((int) node[0])) {
                if (heights[next] > node[3]) continue;
                a[next] += a[(int) node[0]];
                answer += Math.abs(a[(int) node[0]]);
                a[(int) node[0]] = 0;
                queue.offer(new long[]{next, node[0], a[next], heights[next]});
            }
        }
        return answer;
    }
}