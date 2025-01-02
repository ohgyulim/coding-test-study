package hash.level2;

import java.io.*;
import java.util.*;

public class BOJ_수강신청 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int K = Integer.parseInt(input[0]);
        int L = Integer.parseInt(input[1]);

        Map<String, Integer> map = new HashMap<>();
        String[] numbers = new String[L];
        for (int l = 0; l < L; l++) {
            String number = br.readLine();
            map.put(number, map.getOrDefault(number, 0) + 1);
            numbers[l] = number;
        }

        for (int i=0; i<L && K>0; i++ ) {
            if (map.get(numbers[i]) == 1) {
                System.out.println(numbers[i]);
                K--;
            } else {
                map.put(numbers[i], map.get(numbers[i]) - 1);
            }
        }
    }
}
