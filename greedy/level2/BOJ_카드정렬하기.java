package greedy.level2;

import java.io.*;
import java.util.*;


public class BOJ_카드정렬하기 {
    public static void main(String[] args) throws IOException {

        PriorityQueue<Integer> cards = init();
        int answer = 0;
        while (cards.size() > 1) {
            int A = cards.poll();
            int B = cards.poll();

            answer += A+B;
            cards.offer(A+B);
        }

        System.out.println(answer);


    }

    private static PriorityQueue<Integer> init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> cards = new PriorityQueue<>();
        for (int n = 0; n < N; n++) {
            cards.offer(Integer.parseInt(br.readLine()));
        }
        return cards;
    }
}
