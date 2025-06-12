import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int[] dist = new int[N-1];
        for(int i = 1; i < N; i++){
            dist[i-1] = arr[i] - arr[i-1];
        }

        Arrays.sort(dist);
        int res = 0;
        for(int i = 0; i < N-K; i++){
            res += dist[i];
        }

        System.out.println(res);

    }
}