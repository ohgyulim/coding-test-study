import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < n; i++){
            queue.offer(i+1);
        }

        while(queue.size()>1){
            queue.poll();
            queue.offer(queue.poll());
        }
        System.out.println(queue.poll());
    }
}