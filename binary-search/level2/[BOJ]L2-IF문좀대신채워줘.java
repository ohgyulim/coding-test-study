import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<Integer,String> map = new HashMap();
        int[] arr = new int[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            if(!map.keySet().contains(num)){
                map.put(num,str);
            }
            arr[i] = num;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++){
            int left = 0;
            int right = N-1;
            int answer = right;
            int power = Integer.parseInt(br.readLine());
            while(left <= right){
                int mid = (left+right)/2;
                if(arr[mid] >= power){
                    answer = mid;
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }
            sb.append(map.get(arr[answer])+"\n");
        }
        System.out.println(sb.toString().trim());
    }
}