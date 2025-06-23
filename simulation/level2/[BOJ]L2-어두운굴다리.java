import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] position = new int[M];

        for(int i = 0; i < M; i++){
            position[i] = Integer.parseInt(st.nextToken());
        }

        int margin = Math.max(position[0],N-position[M-1]);
        int max = 0;
        for(int i = 0; i < M-1; i++){
            max = Math.max(position[i+1] - position[i], max);
        }
        if((int)Math.ceil(max / 2.0) > margin) System.out.println((int)Math.ceil(max/2.0));
        else System.out.println(margin);
    }
}