import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] agrs) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //[H][W]
        //N M
        //(H-1)/(N+1)
        //(W-1)/(M+1)
        int ans = ((H-1)/(N+1)+1) * ((W-1)/(M+1)+1);
        System.out.println(ans);


    }
}