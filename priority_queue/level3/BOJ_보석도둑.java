package priority_queue.level3;

import java.io.*;
import java.util.*;

public class BOJ_보석도둑 {
    private static class Jewel implements Comparable<Jewel>{
        int weight,price;
        public Jewel (int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
        public int compareTo(Jewel j) {
            return weight - j.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Jewel> jewels = new ArrayList<>();
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(weight, price));
        }

        List<Integer> bags = new ArrayList<>();
        for (int i=0;i<K;i++){
            int bag = Integer.parseInt(br.readLine());
            bags.add(bag);
        }

        Collections.sort(jewels);
        Collections.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        long answer = 0;
        int idx = 0;
        for (int bag : bags) {

            while(idx < N && jewels.get(idx).weight <= bag) { // 현재 가방에서 가능한 보석 다 넣기
                pq.add(jewels.get(idx).price);
                idx++;
            }

            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }

        System.out.println(answer);

    }
}
