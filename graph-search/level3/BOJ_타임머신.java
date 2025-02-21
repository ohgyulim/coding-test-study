import java.io.*;
import java.util.*;

public class _11657_타임머신 {
    static List<Node>[] list;
    static long[] dist;
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new List[N+1];
        dist = new long[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);

        for(int i=0; i<=N; i++){
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b,edge));
        }

        StringBuilder sb = new StringBuilder();
        if(bellmanFord(1)){
            System.out.println(-1);
            return;
        } else{
            for(int i=2; i<=N; i++){
                if(dist[i] == Integer.MAX_VALUE){
                    sb.append("-1\n");
                }else{
                    sb.append(dist[i]+"\n");
                }
            }
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
    public static boolean bellmanFord(int start){
        dist[start] = 0;

        // N번 반복
        for(int i=1; i<=N; i++){
            for(Node x : list[i]){
                int target = x.num;
                //본인이 초기화 안되면 스킵
                if(dist[i] == Integer.MAX_VALUE){
                    break;
                }
                // 기존 값보다 거리가 짧은 경우
                if(dist[target] > dist[i] + x.edge){
                    //만일, N번째에도 값이 갱신되면 음수 순환 존재하는 것
                    if(i==N){
                        return true;
                    }
                    dist[target] = dist[i] + x.edge;
                }
            }
        }
        return false;
    }
    static class Node{
        int num;
        int edge;
        Node(int num, int edge){
            this.num = num;
            this.edge = edge;
        }
    }
}
