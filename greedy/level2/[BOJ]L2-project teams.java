import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 2*Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for(int i = 0; i < n/2; i++){
            min = Math.min(arr[i]+arr[n-i-1], min);
        }
        System.out.println(min);
    }
}