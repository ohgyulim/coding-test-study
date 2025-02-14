// 위상정렬
//https://velog.io/@ddongh1122/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%AA%A8%EB%91%90-0%EC%9C%BC%EB%A1%9C-%EB%A7%8C%EB%93%A4%EA%B8%B0
//262ms, 233MB
import java.util.*;

class Solution {
    public List<List<Integer>> map = new ArrayList<>();
    public int[] inDegree;
    public long[] V;
    public int N;

    public long solution(int[] a, int[][] edges) {
        //초기화
        N = a.length;
        V = new long[N];
        inDegree = new int[N];
        for (int i = 0; i < N; i++) {
            map.add(new ArrayList<>());
            V[i] = a[i];
        }
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            map.get(from).add(to);
            map.get(to).add(from);
            inDegree[from]++;
            inDegree[to]++;
        }

        //위상 정렬 시작
        //1. 차수 1인 지점 넣어주기
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 1)
                q.add(i);
        }

        //2. 위상 정렬 시작
        long result = 0;
        for (int ind = 0; ind < N; ind++) {
            int x = q.poll();
            inDegree[x]--;
            result += Math.abs(V[x]);

            for (int i = 0; i < map.get(x).size(); i++) {
                int y = map.get(x).get(i);
                if (inDegree[y] == 0) continue;
                //종료 조건
                if (inDegree[y] - 1 == 0) {
                    if (V[x] + V[y] != 0)
                        return -1;
                    else
                        return result;
                }
                V[y] += V[x];
                if (--inDegree[y] == 1)
                    q.add(y);
            }
        }
        return 0;
    }
}

//DFS
//https://velog.io/@pkjeogus/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%AA%A8%EB%91%90-0%EC%9C%BC%EB%A1%9C-%EB%A7%8C%EB%93%A4%EA%B8%B0-java
//7 : 1275ms, 232MB
// import java.util.*;

// class Solution {
//     ArrayList<Integer> graph[];
//     long answer = 0;
//     boolean visited[];
//     long atmp[];
//     public long solution(int[] a, int[][] edges) {

//         //안되는 경우 : 가중치의 합이 0이 안되는 경우
//         atmp = new long[a.length];
//         for(int i=0;i<a.length;i++)
//         {
//             atmp[i] = a[i];
//             answer+=atmp[i];
//         }
//         if(answer != 0) return -1;
//         //되는 경우 : 횟수 체크
//         graph = new ArrayList[a.length];
//         visited = new boolean[a.length];

//         for(int i=0;i<a.length;i++)
//         {
//             graph[i] = new ArrayList<>();
//         }

//         for(int[] edge : edges)
//         {
//             graph[edge[0]].add(edge[1]);
//             graph[edge[1]].add(edge[0]);
//         }

//         dfs(0);

//         return answer;
//     }

//     public long dfs(int now)
//     {
//         visited[now] = true;

//         for(int i=0;i<graph[now].size();i++)
//         {
//             int next = graph[now].get(i);
//             if(visited[next]) continue;
//             atmp[now] += dfs(next);
//         }

//         answer += Math.abs(atmp[now]);
//         return atmp[now];
//     }
// }

//BFS
//https://jinyoungchoi95.tistory.com/25
// 4 : 213ms , 223MB
// import java.util.*;

// class Solution {
//     public long[] a;
//     public int n;
//     public long answer = 0;
//     public ArrayList<Integer>[] map;
//     public int[] indegree;
//     public int root = 0;

//     public long solution(int[] atmp, int[][] edges) {
//         n = atmp.length;
//         a = new long[n];
//         for(int i=0; i<n; i++){
//             a[i] = atmp[i];
//             answer += a[i];
//         }
//         if(answer!=0) return -1;
//         if(n==2) return Math.abs(a[0]);

//         map = new ArrayList[n];
//         for(int i=0; i<n; i++){
//             map[i] = new ArrayList<>();
//         }
//         indegree = new int[n];
//         for(int i=0; i<edges.length; i++){
//             map[edges[i][0]].add(edges[i][1]);
//             map[edges[i][1]].add(edges[i][0]);
//             indegree[edges[i][0]]++;
//             indegree[edges[i][1]]++;
//         }
//         Queue<Integer> queue = new LinkedList<>();
//         for(int i=1; i<n; i++){
//             if(map[i].size()==1) queue.add(i);
//         }
//         while(!queue.isEmpty()){
//             int tmp = queue.poll();

//             answer += Math.abs(a[tmp]);
//             indegree[tmp]--;

//             for(int i=0; i<map[tmp].size(); i++){
//                 int next = map[tmp].get(i);
//                 if(indegree[next]==0) continue;
//                 indegree[next]--;
//                 a[next] += a[tmp];
//                 if(indegree[next]==1){
//                     if(next == root) continue;
//                     queue.add(next);
//                 }
//             }
//         }

//         return answer;
//     }
// }