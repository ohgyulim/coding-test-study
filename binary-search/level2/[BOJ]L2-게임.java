import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());

        // 현재 승률
        long currentWinRate = (Y * 100) / X;

        // 소수점 이하는 버리기 때문에 99% 이상이라면 더 이상 승률을 올릴 수 없음
        // 100/100은 처음부터 같기 때문에 이 범위에 포함됨
        //99 다음은 100인데 100도 아니고 99보다 약간이라도 크면 짤리기 때문에 불가능
        if (currentWinRate >= 99) {
            System.out.println(-1);
            return;
        }

        long left = 1, right = 1000000000;
        long result = -1;

        while (left <= right) {
            long mid = (left + right) / 2;
            long newWinRate = ((Y + mid) * 100) / (X + mid);

            if (newWinRate > currentWinRate) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }
}
