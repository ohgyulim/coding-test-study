import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int init = Integer.parseInt(st.nextToken());
        int profit = 0;
        int min = init;
        for(int i = 0; i < n-1 ; i++){
            int now = Integer.parseInt(st.nextToken());
            profit = Math.max(profit, now - min);
            min = Math.min(min, now);
        }
        System.out.println(profit);
    }
}