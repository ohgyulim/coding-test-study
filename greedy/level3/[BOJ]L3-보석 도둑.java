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
        Arrays.sort(jewels, Comparator.comparingInt(j -> j.weight));
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        long maxValue = 0;
        int index = 0;

        for (int bag : bags) {
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