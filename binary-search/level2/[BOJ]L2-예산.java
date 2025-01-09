import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long min = 0;
        long max = 0;
        long mid = 0;
        int[] arr = new int[K];
        for(int i = 0; i < K ; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max,arr[i]);
            min = Math.min(min,arr[i]);
        }
        max++;
        while(min < max){
            int count = 0;
            mid = (min+max)/2;
            for(int i = 0; i < K; i++){
                count+=arr[i]/mid;
            }
            //더 잘게 잘라야함
            if(count < N){
                max = mid;
            }else{
                //더 크게 잘라야함
                min = mid+1;
            }
        }
        System.out.println(min-1);
    }
}