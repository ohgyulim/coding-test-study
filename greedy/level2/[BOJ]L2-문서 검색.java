import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String target = br.readLine();
        int cnt = 0;

        for(int i = 0; i <= str.length()-target.length(); i++){
            if(str.substring(i,i+target.length()).equals(target)){
                cnt++;
                i+= target.length()-1;
            }
        }
        System.out.println(cnt);
    }
}