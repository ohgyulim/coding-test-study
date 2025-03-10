import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(weight, value);
        }

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        //보석 무게로 오름차슈ㅜ
        Arrays.sort(jewels, Comparator.comparingInt(j -> j.weight));
        //가방 무게로 오름차순
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        long maxValue = 0;
        int index = 0;

        for (int bag : bags) {
            // 현재 가방의 무게를 초과하지 않는 보석들을 우선순위 큐에 추가
            while (index < N && jewels[index].weight <= bag) {
                pq.offer(jewels[index].value);
                index++;
            }

            if (!pq.isEmpty()) {
                maxValue += pq.poll();
            }
        }

        System.out.println(maxValue);
    }

    static class Jewel {
        int weight, value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}