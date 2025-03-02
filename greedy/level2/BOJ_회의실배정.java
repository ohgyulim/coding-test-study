package greedy.level2;

import java.util.*;
import java.io.*;

public class BOJ_회의실배정 {
    public static void main(String[] args) throws IOException{
        PriorityQueue<long[]> queue = init();
        int answer = 0;

        long end = 0;
        while(!queue.isEmpty()) {
            long[] meeting = queue.poll();
            if (meeting[0] >= end){
                answer ++;
                end = meeting[1];
            }
        }
        System.out.println(answer);
    }

    public static PriorityQueue<long[]> init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<long[]> queue = new PriorityQueue<>((o1, o2) -> {
          return Long.compare(o1[1],o2[1]) != 0 ? Long.compare(o1[1],o2[1]) : Long.compare(o1[0],o2[0]);
        });

        for (int n=0; n<N; n++) {
            String[] meeting = br.readLine().split(" ");
            queue.offer(new long[] {Long.parseLong(meeting[0]), Long.parseLong(meeting[1])});
        }
        return queue;
    }
}
