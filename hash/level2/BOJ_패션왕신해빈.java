package hash.level2;

import java.util.*;
import java.io.*;

public class BOJ_패션왕신해빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int t = 0;
        while (t++ < T) {
            int N = Integer.parseInt(br.readLine());
            Map<String, Integer> clothes = new HashMap<>();
            for (int n = 0; n < N; n++) {
                String kind = br.readLine().split(" ")[1];
                clothes.put(kind, clothes.getOrDefault(kind, 0) + 1);
            }

            int answer = 1;
            for (String kind : clothes.keySet()) {
                answer *= clothes.get(kind) + 1;
            }
            System.out.println(answer - 1);


        }
    }
}
