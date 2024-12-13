import java.io.*;
import java.util.*;

public class BJ_국회의원_선거 {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        PriorityQueue<Integer> pQueue = new PriorityQueue<>(Collections.reverseOrder());

        int dasom = Integer.parseInt(bf.readLine());

        for (int i = 1; i < N; i++) {
            pQueue.add(Integer.parseInt(bf.readLine()));
        }

        int count = 0;

        while (!pQueue.isEmpty()) {
            int pq = pQueue.poll();

            if (pq >= dasom) {
                pQueue.add(pq - 1);
                dasom++;
                count++;
            } else {
                break;
            }
        }

        System.out.println(count);
    }
}