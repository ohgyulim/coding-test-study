import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        for(int i=0;i<sources.length;i++){
            answer[i] = bfs(sources[i], roads, destination);
        }

        return answer;
    }

    private int bfs(int start, int[][] roads, int destination){
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[100001];
        int depth = 0;

        queue.add(start);

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i=0;i<size;i++){
                int q = queue.poll();
                if(visited[q]==1) continue;
                if(q==destination) return depth;
                visited[q] = 1;

                for(int j=0;j<roads.length;j++){
                    if(roads[j][0]==q && visited[roads[j][1]]==0){
                        queue.add(roads[j][1]);
                    }else if(roads[j][1]==q && visited[roads[j][0]]==0){
                        queue.add(roads[j][0]);
                    }
                }
            }
            depth++;
        }
        return -1;
    }
}