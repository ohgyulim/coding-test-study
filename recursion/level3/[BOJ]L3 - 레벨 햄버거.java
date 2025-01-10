import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long X = Long.parseLong(st.nextToken());
        long[] layer = new long[N + 1];
        long[] patty = new long[N + 1];
        layer[0] = 1;
        patty[0] = 1;

        for (int i = 1; i <= N; i++) {
            layer[i] = 2 * layer[i - 1] + 3;
            patty[i] = 2 * patty[i - 1] + 1;
        }

        System.out.println(countPatty(N, X, layer, patty));
    }

    // 먹은 패티 계산
    private long countPatty(int level, long x, long[] layer, long[] patty) {
        if (level == 0) {
            // 레벨-0 버거는 패티 하나
            return x == 1 ? 1 : 0;
        }

        if (x == 1) {
            return 0;
        }

        if (x <= layer[level - 1] + 1) {
            return countPatty(level - 1, x - 1, layer, patty);
        }

        if (x == layer[level - 1] + 2) {
            return patty[level - 1] + 1;
        }

        if (x <= 2 * layer[level - 1] + 2) {
            return patty[level - 1] + 1 + countPatty(level - 1, x - (layer[level - 1] + 2), layer, patty);
        }

        return patty[level];
    }
}
