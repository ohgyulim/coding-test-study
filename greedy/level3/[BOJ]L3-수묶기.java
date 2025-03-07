import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();
        int result = 0;
        boolean hasZero = false;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                positives.add(num);
            } else if (num == 1) {
                result += 1;
            } else if (num == 0) {
                hasZero = true;
            } else {
                negatives.add(num);
            }
        }

        Collections.sort(positives, Collections.reverseOrder());
        for (int i = 0; i < positives.size(); i += 2) {
            if (i + 1 < positives.size()) {
                result += positives.get(i) * positives.get(i + 1);
            } else {
                result += positives.get(i);
            }
        }

        Collections.sort(negatives);
        for (int i = 0; i < negatives.size(); i += 2) {
            if (i + 1 < negatives.size()) {
                result += negatives.get(i) * negatives.get(i + 1);
            } else if (!hasZero) {
                result += negatives.get(i);
            }
        }

        System.out.println(result);
    }
}