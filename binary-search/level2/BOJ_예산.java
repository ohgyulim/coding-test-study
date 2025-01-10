import java.io.*;
import java.util.*;

public class _2512_예산 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int left=0;
        int right= 0;
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, arr[i]);
        }

        int m = Integer.parseInt(br.readLine());
        while(left<=right) {
            int sum = (left+right)/2;
            long budget =0;
            for(int i=0; i<n; i++) {
                if(arr[i]>mid) sum += mid;
                else sum+= arr[i];
            }
            if(sum<=m) {
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        System.out.println(right);
    }
}