import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //0~X까지의 방문자 총합
        int max = 0;
        for(int i = 0; i < X; i++){
            max += arr[i];
        }

        //0~X까지 방문자 총합에서 구간 최소 인덱스를 뺴고 다음 인덱스를 더하며 max갱신
        int start = max;
        int range = max;
        for(int i = 0; i < N-X; i++){
            range = range-arr[i]+arr[X+i];
            max = Math.max(max, range);
        }

        if(max == 0) System.out.println("SAD");
        else{
            int cnt = 0;
            if(start == max) cnt++;
            for(int i = 0; i < N-X; i++){
                start = start-arr[i]+arr[X+i];
                if( start == max) cnt++;
            }
            System.out.println(max);
            System.out.println(cnt);
        }

    }
}