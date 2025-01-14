class Solution {

    int maxSheep =0;

    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        boolean[] visited = new boolean[info.length];

        dfs(0,visited,0,0,info,edges);
        return maxSheep;
    }
    public void dfs(int nodeIdx,boolean[] visited, int sheep,int wolf, int[] info, int[][] edges){
//        System.out.print("nodeIdx : " + nodeIdx);
        visited[nodeIdx] = true;
        //양 일 경우
        if(info[nodeIdx] == 0){
            sheep++;
            maxSheep = Math.max(maxSheep,sheep);
//            System.out.println(" 양이다");
        }
        // 늑대 일 경우
        else{
            wolf++;
//            System.out.println(" 늑대다");
        }
        if(wolf>= sheep){
            return;
        }
        for(int[] edge : edges){
            // 부모노드는 방문했는데, 자식은 안 한 경우
            if(visited[edge[0]] && !visited[edge[1]]){
                dfs(edge[1],visited,sheep,wolf,info, edges);
                visited[edge[1]] = false;
            }
        }
    }
}