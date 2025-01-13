package binary_search.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_기타_레슨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int min = 0; // 레코드 중, 가장 길이가 긴 것
        int max = 0;

        int[] lessons = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            lessons[i] = Integer.parseInt(st.nextToken());
            max += lessons[i];
            if (min < lessons[i]) {
                min = lessons[i];
            }
        }

        while (min < max) {
            int mid = (min + max) / 2;

            int sum = 0;
            int cnt = 1;
            for (int i=0;i<N;i++) {
                if (sum + lessons[i] <= mid) {
                    sum += lessons[i];
                }
                else {
                    cnt++;
                    sum = lessons[i];
                }
            }

            if (cnt <= M) { // 레코드 하나의 크기를 더 작게해서 더 많은 조각이 나오게
                max = mid;
            }
            else {
                min = mid + 1;
            }
        }

        System.out.println(min);
    }
}
