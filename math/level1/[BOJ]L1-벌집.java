import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        //1 ~ 6(0)+1
        //6(0)+2 ~ 6(1)+1
        //6(0+1)+2 ~ 6(3)+1;
        //6(0+1+2)+2 ~ 6(6)+1;
        //6(10)+1;
        //6(15)+1;
        int i = 0;
        int calc = 0;
        int sum = 0;
        while(true){
            sum += i;
            calc = 6*sum+1;
            if(calc >= n) break;
            i++;
        }
        System.out.println(i+1);
    }
}