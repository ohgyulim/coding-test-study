import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] lectures = new int[n];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            lectures[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, lectures[i]);
            sum += lectures[i];
        }

        int left = max;
        int right = sum;
        int result = sum;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(lectures, m, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    public static boolean isPossible(int[] lectures, int m, int size) {
        int count = 1;
        int total = 0;

        for (int lecture : lectures) {
            if (total + lecture > size) {
                //하나더 필요
                count++;
                //다음 블루레이에 현재 강의 담기
                total = lecture;

                if (count > m) {
                    return false;
                }
            } else {
                total += lecture;
            }
        }

        return true;
    }
}