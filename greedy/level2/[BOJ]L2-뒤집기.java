import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int zeroCnt = 0;
        int oneCnt = 0;
        char before = '-';

        for(int i = 0; i < str.length(); i++){
            char now = str.charAt(i);
            if(now == '0' && now != before){
                zeroCnt++;
                before = '0';
            }else if(now == '1' && now != before){
                oneCnt++;
                before = '1';
            }
        }
        System.out.println(Math.min(oneCnt,zeroCnt));
    }
}