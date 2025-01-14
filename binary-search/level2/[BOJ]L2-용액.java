import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int maxSum = Integer.MAX_VALUE;
        int left = 0;
        int right = n-1;
        int answerLeft = 0;
        int answerRight = 0;

        while(left<right){
            int sum = arr[left]+arr[right];
            if(Math.abs(sum) < maxSum){
                maxSum = Math.abs(sum);
                answerLeft = arr[left];
                answerRight = arr[right];
            }

            if(sum < 0){
                left++;
            }else{
                right--;
            }
        }
        System.out.println(answerLeft+ " "+ answerRight);
    }

}