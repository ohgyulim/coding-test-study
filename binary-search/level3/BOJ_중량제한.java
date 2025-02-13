import java.util.*;
import java.io.*;

class Main {
    static int N, M, fac1, fac2;
    static List<List<Edge>> graph;

    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        int left = 1, right = 0;  // 최소 무게와 최대 무게
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken()) - 1;
            int n2 = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            graph.get(n1).add(new Edge(n2, w));
            graph.get(n2).add(new Edge(n1, w));

            right = Math.max(right, w); // 최대 중량 설정
        }

        st = new StringTokenizer(bf.readLine());
        fac1 = Integer.parseInt(st.nextToken()) - 1;
        fac2 = Integer.parseInt(st.nextToken()) - 1;

        // 이진 탐색을 통한 최대 중량 탐색
        int result = left;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (bfs(mid)) { // 현재 중량으로 이동 가능하면 더 높은 값 탐색
                result = mid;
                left = mid + 1;
            } else { // 불가능하면 낮은 값 탐색
                right = mid - 1;
            }
        }

        System.out.println(result);
    }

    // BFS를 사용하여 주어진 무게 `weight` 이상으로 fac1 -> fac2 이동 가능한지 판별
    static boolean bfs(int weight) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];

        queue.add(fac1);
        visited[fac1] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == fac2) return true;

            for (Edge next : graph.get(cur)) {
                if (!visited[next.to] && next.weight >= weight) {
                    visited[next.to] = true;
                    queue.add(next.to);
                }
            }
        }
        return false;
    }
}


// 아래는 메모리 초과

//class Main{
//    public static int N;
//    public static int M;
//    public static int fac1;
//    public static int fac2;
//    public static int[][] nodes;
//    public static boolean[] visited;
//
//    public static void main(String[] args) throws IOException{
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//
//        nodes = new int[N][N];
//
//        for(int i=0;i<M;i++){
//            st = new StringTokenizer(bf.readLine());
//            int n1 = Integer.parseInt(st.nextToken());
//            int n2 = Integer.parseInt(st.nextToken());
//            int w = Integer.parseInt(st.nextToken());
//            nodes[n1-1][n2-1]=w;
//            nodes[n2-1][n1-1]=w;
//        }
//
//        st = new StringTokenizer(bf.readLine());
//        fac1 = Integer.parseInt(st.nextToken())-1;
//        fac2 = Integer.parseInt(st.nextToken())-1;
//
//        visited = new boolean[N];
//
//        int max = 0;
//        for(int i=0;i<N;i++){
//            if(nodes[fac1][i]>0){
//                max = Math.max(max,dfs(nodes[fac1][i], i));
//            }
//        }
//
//        System.out.println(max);
//    }
//
//    private static int dfs(int min, int node){
//        if(node==fac2){
//            return min;
//        }
//
//        visited[node]=true;
//        int max = 0;
//
//        for(int i=0;i<N;i++){
//            if(nodes[node][i]>0 && !visited[i]){
//                max = Math.max(max, dfs(Math.min(min, nodes[node][i]),i));
//            }
//        }
//
//        visited[node] = false;
//
//        return max;
//    }
//}