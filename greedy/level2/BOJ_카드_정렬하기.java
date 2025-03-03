package greedy.level2;

import java.io.*;
import java.util.*;

public class BOJ_카드_정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i=0 ; i<N;i++){
            pq.add(Long.parseLong(br.readLine()));
        }

        Long result = 0L;
        while(pq.size() > 1){
            Long num1 = pq.poll();
            Long num2 = pq.poll();

            result += num1 + num2;
            pq.add(num1 + num2);
        }

        System.out.println(result);

    }
}
