import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int J = Integer.parseInt(br.readLine());
        int[] arr = new int[J];

        for(int i = 0; i < J; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        int left = 1;
        int right = M;
        int dist = 0;
        for(int i = 0; i < J; i++){
            if(arr[i] < left){
                dist += left-arr[i];
                right -=left-arr[i];
                left = arr[i];

            }else if(arr[i] > right){
                dist += arr[i]-right;
                left += arr[i]-right;
                right = arr[i];
            }
        }
        System.out.println(dist);

    }
}