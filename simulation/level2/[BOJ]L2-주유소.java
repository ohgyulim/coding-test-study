import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] road = new int[N];
        int[] price = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N-1; i++){
            road[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            price[i] = Integer.parseInt(st.nextToken());
        }

        long total = 0;
        int min = price[0];
        for(int i = 0; i < N-1; i++){
            min = Math.min(price[i],min);
            total += (long)min * road[i];
        }
        System.out.println(total);

    }
}