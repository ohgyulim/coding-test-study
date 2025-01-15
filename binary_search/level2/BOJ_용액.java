package binary_search.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] values = new int[N];
        int i = 0;
        for (String value : input) {

            values[i++] = Integer.parseInt(value);
        }

        int left = 0;
        int right = N-1;

        int answerLeft = values[0];
        int answerRight = values[N-1];
        int sum = Integer.MAX_VALUE;
        while (left < right) {
            int tmp = values[left] + values[right];
            if (Math.abs(tmp) < sum) {
                sum = Math.abs(tmp);
                answerLeft = values[left];
                answerRight = values[right];
            }

            if (tmp < 0) {
                left ++;
            } else if (tmp > 0) {
                right --;
            } else {
                break;
            }
        }

        System.out.println(answerLeft + " " + answerRight);
    }
}
