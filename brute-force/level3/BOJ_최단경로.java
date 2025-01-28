import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken())+1;
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(br.readLine());

        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> {
            return o1.weight - o2.weight;
        });
        boolean[] V = new boolean[N];
        ArrayList<Node>[]A = new ArrayList[N];
        int[] distance = new int[N];

        for(int i=1; i<N; i++){
            A[i] = new ArrayList<>();
            distance[i] = INF;
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            A[a].add(new Node(b, w));
        }

        q.add(new Node(S, 0));
        distance[S] = 0;
        while (!q.isEmpty()){
            Node now = q.poll();
            if(V[now.dest]) continue;
            V[now.dest] = true;
            for (Node x : A[now.dest]){
                if(distance[x.dest] > distance[now.dest] + x.weight) {
                    distance[x.dest] = distance[now.dest] + x.weight;
                    q.add(new Node(x.dest, distance[x.dest]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<N; i++){
            sb.append(V[i] ? distance[i] : "INF").append("\n");
        }

        System.out.println(sb);

    }
    static class Node{
        int dest;
        int weight;
        Node(int dest, int weight){
            this.dest=dest;
            this.weight = weight;
        }
    }
}