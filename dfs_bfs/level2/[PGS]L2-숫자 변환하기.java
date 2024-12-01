import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        if(x == y){
            return 0;
        }

        boolean[] visited = new boolean[y+1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,0});
        visited[x] = true;

        while(!queue.isEmpty()){
            int current[] = queue.poll();
            int value = current[0];
            int cnt = current[1];

            int possibleValue[] = new int[]{value * 2, value * 3, value + n};
            for(int next : possibleValue){
                if(next == y){
                    return cnt+1;
                }

                if(next <= y && !visited[next]){
                    visited[next] = true;
                    queue.add(new int[]{next, cnt+1});
                }
            }
        }

        return -1;
    }
}