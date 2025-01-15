package binary_search.level3;

import java.util.*;
import java.io.*;

public class BOJ_두_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> values = new ArrayList<>(N);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            values.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(values);

        int start = 0;
        int end = N-1;
        int min = Integer.MAX_VALUE;
        int result[] = new int[2];
        int sum;
        while (start < end){

            sum = values.get(start) + values.get(end);

            if (Math.abs(sum) < min){
                min = Math.abs(sum);
                result[0] = values.get(start);
                result[1] = values.get(end);

                if (min == 0)
                    break;
            }

            if (sum < 0)
                start++;
            else
                end--;
        }

        System.out.print(result[0]+" "+result[1]);
    }
}

