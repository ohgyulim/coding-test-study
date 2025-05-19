import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new LinkedList();
        boolean[] visited = new boolean[k+1];
        queue.offer(new int[]{a,0});
        visited[a] = true;
        int count = 0;

        //count는 현재 bfs의 depth로 세줘야함
        //해당 depth에서 +1, *2 두개를 선택 할 수 있음

        while(!queue.isEmpty()){
            int[] value = queue.poll();
            int number = value[0];
            count = value[1];

            if(number == k) break;
            if(number*2 <= k && !visited[number*2]){
                queue.offer(new int[]{number*2,count+1});
                visited[number*2] = true;
            }
            if(number+1 <= k && !visited[number+1]){
                queue.offer(new int[]{number+1,count+1});
                visited[number+1] = true;
            }

        }

        System.out.println(count);
    }
}