import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 입력값 N

        int bags = -1;

        for (int x = N / 5; x >= 0; x--) {
            int remaining = N - (x * 5);
            if (remaining % 3 == 0) {
                bags = x + (remaining / 3);
                break;
            }
        }

        System.out.println(bags);
    }
}