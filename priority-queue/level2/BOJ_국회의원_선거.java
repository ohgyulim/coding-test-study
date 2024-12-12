package priority-queue.level2;

import java.util.*;
import java.io.*;

public class BOJ_국회의원_선거 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int dasom = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i=0;i<N-1;i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        while (N!=1 && dasom <= pq.peek()){
            answer++;
            dasom++;
            int cnt = pq.poll() - 1;
            pq.add(cnt);
        }

        System.out.println(answer);

    }
}
