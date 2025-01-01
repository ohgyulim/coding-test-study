package implementation.level2;

import java.util.*;
import java.io.*;

public class BOJ_패션왕_신해빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i=0;i<t;i++) {
            int n = Integer.parseInt(br.readLine());
            Map<String, Integer> clothes = new HashMap<>();
            for (int j=0;j<n;j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String clothe = st.nextToken();
                String type = st.nextToken();
                clothes.put(type, clothes.getOrDefault(type,0)+1);
            }

            int cnt = 1;
            for (Map.Entry<String, Integer> entry : clothes.entrySet()) {
                cnt *= (entry.getValue() + 1);
            }
            System.out.println(cnt-1);
        }
    }
}