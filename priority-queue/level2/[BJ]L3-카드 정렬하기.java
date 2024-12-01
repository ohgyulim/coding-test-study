import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long res = 0;
        for(int i = 0; i < n; i++){
            minHeap.add(Long.parseLong(br.readLine()));
        }
        while (minHeap.size() > 1) {
            long a = minHeap.poll();
            long b = minHeap.poll();
            long sumAB = a + b;
            res += sumAB;
            minHeap.add(sumAB);
        }
        System.out.println(res);
    }
}