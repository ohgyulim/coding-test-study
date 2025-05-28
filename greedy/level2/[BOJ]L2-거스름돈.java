import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = Integer.MAX_VALUE;

        for(int i = 0; i <= n/5; i++){
            if((n-(5*i))%2 == 0){
                cnt = Math.min(cnt,i+(n-(5*i))/2);
            }
        }


        System.out.println(cnt== Integer.MAX_VALUE ? -1 : cnt);
    }
}