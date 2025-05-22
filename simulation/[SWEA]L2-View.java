import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 10; i++){
            int n = Integer.parseInt(br.readLine());
            int [] buildings = new int[n];
            int sum = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                buildings[j] = Integer.parseInt(st.nextToken());
            }

            //1~n-2까지 돌면서 좌우 중 큰 수와의 차이가 양수일때 현재 수-구한 수 -1
            for(int j = 2; j < n-2; j++){
                int lMax = Math.max(buildings[j-2], buildings[j-1]);
                int rMax = Math.max(buildings[j+1], buildings[j+2]);
                int max = Math.max(lMax, rMax);
                if(buildings[j] > max){
                    sum += buildings[j]-max;
                }
            }
            System.out.printf("#%d %d\n",i+1, sum);

        }

    }
}
