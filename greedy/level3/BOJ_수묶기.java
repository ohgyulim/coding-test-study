package greedy.level3;

import java.io.*;
import java.util.*;

public class BOJ_수묶기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> plusArr = new ArrayList<>();
        List<Integer> minusArr = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            int k = Integer.parseInt(br.readLine());
            if (k > 0) {
                plusArr.add(k);
            } else {
                minusArr.add(k);
            }
        }

        plusArr.sort(Collections.reverseOrder());
        Collections.sort(minusArr);

        List<Integer> answer = new ArrayList<>();

        while (plusArr.size() > 1) {
            int a = plusArr.get(0);
            int b = plusArr.get(1);
            if (a * b > a + b) {
                answer.add(a*b);
                plusArr.remove(0);
                plusArr.remove(0);
            } else {
                answer.add(a);
                plusArr.remove(0);
            }
        }
        while (!plusArr.isEmpty()) {
            answer.add(plusArr.get(0));
            plusArr.remove(0);
        }

        while (minusArr.size() > 1) {
            int a = minusArr.get(0);
            int b = minusArr.get(1);
            if (a * b > a + b) {
                answer.add(a*b);
                minusArr.remove(0);
                minusArr.remove(0);
            } else {
                answer.add(a);
                minusArr.remove(0);
            }
        }
        while (!minusArr.isEmpty()) {
            answer.add(minusArr.get(0));
            minusArr.remove(0);
        }

        int answerSum = 0;
        for (int a : answer ){
            answerSum += a;
        }
        System.out.println(answerSum);
    }
}
