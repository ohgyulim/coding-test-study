package greedy.level3;

import java.util.*;
import java.io.*;

public class BOJ_보석도둑 {
    public static class Jewel implements Comparable<Jewel> {
        int m;
        int v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel o) {
            return this.m - o.m;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Jewel> queue = new PriorityQueue<>();
        for (int n=0; n<N; n++) {
            st = new StringTokenizer(br.readLine());
            queue.offer(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        List<Integer> bags = new ArrayList<>();
        for (int k=0; k<K; k++) {
            bags.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(bags);

        PriorityQueue<Jewel> tmpQueue = new PriorityQueue<>((o1, o2) -> o2.v - o1.v);
        long answer = 0;
        for (int bag : bags) {
            Jewel mxJewel = tmpQueue.poll();
            while (!queue.isEmpty()) {
                Jewel jewel = queue.poll();
                if (jewel.m <= bag) {
                    if (mxJewel == null) {
                        mxJewel = jewel;
                    } else if (mxJewel.v < jewel.v) {
                        tmpQueue.offer(mxJewel);
                        mxJewel = jewel;
                    } else {
                        tmpQueue.offer(jewel);
                    }
                } else {
                    queue.offer(jewel);
                    break;
                }
            }
            if (mxJewel != null) {
                answer += mxJewel.v;
            }
        }
        System.out.println(answer);
    }
}
