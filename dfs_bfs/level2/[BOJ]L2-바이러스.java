import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(br.readLine());
        int e = Integer.parseInt(br.readLine());
        Map<Integer,List<Integer>> edges = new HashMap();
        boolean[] visited = new boolean[v+1];

        //양방향 방문 처리
        StringTokenizer st;
        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            edges.computeIfAbsent(start, k -> new ArrayList<>()).add(next);
            edges.computeIfAbsent(next, k -> new ArrayList<>()).add(start);
        }

        Queue<Integer> queue = new LinkedList();
        queue.offer(1);
        visited[1] = true;
        int count = 0;

        while(!queue.isEmpty()){
            int curr = queue.poll();
            if(edges.get(curr)!= null){
                for(int i : edges.get(curr)){
                    if(!visited[i]){
                        queue.offer(i);
                        visited[i] = true;
                        count++;
                    }
                }
            }

        }
        System.out.println(count);
    }
}