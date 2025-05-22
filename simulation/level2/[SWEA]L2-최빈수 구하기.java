import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(br.readLine());
            int[] count = new int[101];
            int max = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 1000; j++){
                int a = Integer.parseInt(st.nextToken());
                count[a]++;
                if(count[a] > count[max]){
                    max = a;
                } else if (count[a] == count[max]) {
                    max = Math.max(max, a);
                }
            }
            System.out.printf("#%d %d\n",n,max);
        }
    }
}
