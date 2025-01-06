import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
public class _2805_나무자르기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        int left = 0;
        int right = -1;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            right = Math.max(arr[i],right);
        }

        while(left<=right){
            int mid= (left + right) / 2;
            long sum = 0;
            for(int i=0; i<N; i++){
                // 음수 처리
                if(arr[i]>mid) {
                    sum += (arr[i] - mid);
                }
            }
//            System.out.println(mid +" " + sum);

            //합계가 낮다는 것은 더 아래에서 잘라야함을 의미
            if(sum<M){
                right = mid-1;
            }
            //합계가 높다는 것은 더 위에서 잘라야함을 의미
            else{
                left = mid+1;
            }
        }
        System.out.println(right);
    }
}
