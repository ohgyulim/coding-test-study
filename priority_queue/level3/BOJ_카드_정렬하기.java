package priority_queue.level3;

import java.util.*;
import java.io.*;

public class BOJ_카드_정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            queue.offer(Integer.parseInt(br.readLine()));
        }
        while (queue.size() > 1) {
            int x = queue.poll();
            int y = queue.poll();
            answer += x+y;
            queue.offer(x+y);
        }
        System.out.println(answer);
    }
}
