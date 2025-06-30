import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];
        int[] answer = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int tallerCount = input[i];
            int person = i + 1;

            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if (answer[j] == 0) {
                    if (cnt == tallerCount) {
                        answer[j] = person;
                        break;
                    }
                    cnt++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
