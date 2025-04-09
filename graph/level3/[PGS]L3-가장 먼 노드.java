import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        List<List<Integer>> graph = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] e : edge){
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        int[] distance = new int[n+1];
        boolean[] visited = new boolean[n+1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1]=true;

        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int next : graph.get(current)){
                if(!visited[next]){
                    visited[next] = true;
                    distance[next] = distance[current]+1;
                    queue.add(next);
                }
            }
        }

        int max = 0;
        for(int m : distance){
            max = Math.max(max,m);
        }

        int count = 0;
        for(int m : distance){
            if(m == max) count++;
        }

        return count;
    }
}