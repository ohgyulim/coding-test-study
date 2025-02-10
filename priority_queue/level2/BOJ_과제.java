package priority-queue.level2;

import java.util.*;
import java.io.*;

public class BOJ_과제 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<Integer, PriorityQueue> map = new HashMap<>();
        int maxKey = Integer.MIN_VALUE;

        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            maxKey = Math.max(maxKey, key);

            if (map.containsKey(key)) {
                map.get(key).add(value);
                continue;
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            pq.add(value);
            map.put(key, pq);
        }

        int answer = 0;
        for (int i=maxKey;i>=1;i--) {
            int targetKey = 0;
            int max = Integer.MIN_VALUE;
            for (int j=maxKey;j>=i;j--) {
                if (!map.containsKey(j) || map.get(j).isEmpty()) {
                    continue;
                }

                if ((int) map.get(j).peek() > max) {
                    targetKey = j;
                    max = (int) map.get(j).peek();
                }
            }

            if (max != Integer.MIN_VALUE) {
                answer += max;
                map.get(targetKey).poll();
            }
        }

        System.out.println(answer);

    }
}
