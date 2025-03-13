import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] weights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weights);

        int minWeight = 1;

        for (int weight : weights) {
            if (weight > minWeight) {
                break;
            }
            minWeight += weight;
        }

        System.out.println(minWeight);
    }
}
