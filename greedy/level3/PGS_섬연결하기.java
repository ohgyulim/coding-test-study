package greedy.level3;

import java.util.*;

class PGS_섬연결하기 {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        int[] visited = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        List<List<int[]>> costList = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            costList.add(new ArrayList<>());
        }

        for (int[] cost : costs){
            int a = cost[0];
            int b = cost[1];
            int c = cost[2];

            costList.get(a).add(new int[] {b, c});
            costList.get(b).add(new int[] {a, c});
        }

        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0,0});
        visited[0] = 0;

        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            if (node[1] != visited[node[0]]) continue;
            List<int[]> newNodes = costList.get(node[0]);
            for (int[] newNode : newNodes) {
                if (visited[newNode[0]] > newNode[1]) {
                    queue.add(new int[] {newNode[0], newNode[1]});
                    visited[newNode[0]] = newNode[1];
                }
            }
        }

        for (int cost : visited) {
            //System.out.println(cost);
            answer += cost;
        }

        return answer;
    }
}