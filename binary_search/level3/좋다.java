package binary_search.level3;

import java.io.*;
import java.util.*;

public class 좋다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(numbers);
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N-1;
            while (left < right) {
                if (left == i) {
                    left ++;
                    continue;
                } else if (right == i) {
                    right --;
                    continue;
                }
                if (numbers[left] + numbers[right] == numbers[i]) {
                    answer++;
                    break;
                } else if (numbers[left] + numbers[right] > numbers[i]) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        System.out.println(answer);
    }
}
