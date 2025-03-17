import java.util.*;

class Solution {
    int max = 0;

    public int solution(int k, int[][] dungeons) {
        boolean[] isVisited = new boolean[dungeons.length];
        dfs(k,dungeons,isVisited, 0);
        return max;
    }

    private void dfs(int k,int[][] dungeons ,boolean[] isVisited, int count){
        max = Math.max(max,count);

        for(int i = 0; i < dungeons.length; i++){
            if(!isVisited[i] && dungeons[i][0] <= k){
                isVisited[i] = true;
                dfs(k-dungeons[i][1],dungeons, isVisited, count+1);
                isVisited[i] = false;
            }
        }
    }
}