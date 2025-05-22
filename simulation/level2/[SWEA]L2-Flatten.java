import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 10; i++){
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[100];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 100; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }

            for(int j = 0; j < n; j++){
                Arrays.sort(arr);
                arr[0]++;
                arr[99]--;
            }

            Arrays.sort(arr);
            System.out.printf("#%d %d\n",i+1, arr[99]-arr[0]);
        }

    }
}
