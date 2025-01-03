package implementation.level2;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class BOJ_수강신청 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        LinkedHashSet<String> registration = new LinkedHashSet<>();
        for (int i=0;i<L;i++) {
            String num = br.readLine();
            if (registration.contains(num)) {
                registration.remove(num);
            }
            registration.add(num);
        }

        int cnt = 0;
        for (String student : registration) {
            cnt++;
            System.out.println(student);
            if (cnt == K) {
                break;
            }
        }

    }
}