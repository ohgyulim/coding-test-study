package implementation.level2;

import java.util.*;
import java.io.*;

public class BOJ_수묶기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i=0;i<N;i++) {
            int num = Integer.parseInt(br.readLine());
            pq.add(num);
        }

        int answer = 0;
        while(pq.size() > 1) {
            int op1 = pq.poll();
            int op2 = pq.poll();

            int plus = op1 + op2;
            int multiply = op1 * op2;

            if (multiply > plus) {
                answer += multiply;
            }
            else {
                answer += op1;
                pq.add(op2);
            }
        }

        if (!pq.isEmpty()) {
            answer += pq.poll();
        }

        System.out.println(answer);
    }
}