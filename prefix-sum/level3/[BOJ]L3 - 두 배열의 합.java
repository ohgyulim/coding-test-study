import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> subA = getSubArraySums(A);
        List<Integer> subB = getSubArraySums(B);

        Map<Integer, Integer> mapB = new HashMap<>();
        for (int sum : subB) {
            mapB.put(sum, mapB.getOrDefault(sum, 0) + 1);
        }

        long result = 0;
        for (int sumA : subA) {
            int target = T - sumA;
            if (mapB.containsKey(target)) {
                result += mapB.get(target);
            }
        }

        System.out.println(result);
    }

    private static List<Integer> getSubArraySums(int[] array) {
        List<Integer> subArraySums = new ArrayList<>();
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += array[j];
                subArraySums.add(sum);
            }
        }
        return subArraySums;
    }
}
