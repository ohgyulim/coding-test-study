package binary_search.level2;

import java.io.*;
import java.util.*;

public class BOJ_숫자카드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] CARDS = br.readLine().split(" ");
        int[] cards = new int[CARDS.length];
        int i = 0;
        for (String card : CARDS) {
            cards[i++] = Integer.parseInt(card);
        }
        Arrays.sort(cards);

        int M = Integer.parseInt(br.readLine());
        String[] targets = br.readLine().split(" ");
        for (String target : targets) {
            System.out.print(containsCard(Integer.parseInt(target), cards) + " ");
        }

    }

    private static int containsCard(int card, int[] cards) {
        int left = 0;
        int right = cards.length - 1;

        while (left <= right) {
            int mid = (left+right) / 2;
            if (cards[mid] == card) {
                return 1;
            } else if (cards[mid] > card) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return 0;
    }
}
