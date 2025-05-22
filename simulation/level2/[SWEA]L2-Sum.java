import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 10; i++){
            int n = Integer.parseInt(br.readLine());
            int max = 0;
            int[][] arr = new int[100][100];
            for(int j = 0; j < 100; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k = 0; k < 100; k++){
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            for(int j = 0; j < 100; j++){
                int colSum = 0;
                int rowSum = 0;
                for(int k = 0; k < 100; k++){
                    rowSum += arr[j][k];
                    colSum += arr[k][j];
                }
                max = Math.max(max, Math.max(colSum, rowSum));
            }

            int lDiagonal = 0;
            int rDiagonal = 0;

            for(int j = 0; j < 100; j++){
                lDiagonal += arr[j][j];
                rDiagonal += arr[99-j][j];
            }
            max = Math.max(max, Math.max(lDiagonal, rDiagonal));

            System.out.printf("#%d %d\n",n, max);
        }

    }
}
