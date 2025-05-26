import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());
        long n = 0;
        long sum = 0;
        while(sum + (n+1) <= S){
            n++;
            sum += n;
        }
        System.out.println(n);
    }
}