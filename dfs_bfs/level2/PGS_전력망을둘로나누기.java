package dfs_bfs.level2;

import java.util.*;
import static java.lang.Math.abs;

class PGS_전력망을둘로나누기 {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        List<List<Integer>> wireList = new ArrayList<>(n);
        for (int i = 0; i < n + 1; i++) {
            wireList.add(new ArrayList<>());
        }
        for (int[] wire : wires) {
            wireList.get(wire[0]).add(wire[1]);
            wireList.get(wire[1]).add(wire[0]);
        }
        for(int i =0; i<wireList.size();i++){
            for(int j=0;j<wireList.get(i).size();j++)  {
                System.out.print(wireList.get(i).get(j)+ " ");
            }
            System.out.println();
        }

        for (int i = 0; i < n - 1; i++) {
            boolean[] visited = new boolean[n + 1];
            int cutX = wires[i][0];
            int cutY = wires[i][1];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(1);
            visited[1] = true;
            while (!queue.isEmpty()) {
                int x = queue.poll();
                for (int j = 0; j < wireList.get(x).size(); j++) {
                    if (visited[wireList.get(x).get(j)] || (cutX == x && cutY == wireList.get(x).get(j)) || (cutY == x && cutX == wireList.get(x).get(j))) {
                        continue;
                    }
                    visited[wireList.get(x).get(j)] = true;
                    queue.offer(wireList.get(x).get(j));
                }
            }
            int cnt = 0;
            for (int k = 0; k < n + 1; k++) {
                if (visited[k]){
                    cnt ++;
                }
            }
            answer = Math.min(answer, abs(cnt-(n-cnt)));
        }
        return answer;
    }
}
