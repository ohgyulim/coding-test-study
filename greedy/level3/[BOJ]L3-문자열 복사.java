import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        String target = br.readLine();

        String tmp = "";
        int cnt = 0;

        for(int i = 0; i < target.length(); i++){
            tmp += target.charAt(i);
            if(origin.contains(tmp) &&
                    (i == target.length()-1 || !origin.contains(tmp+target.charAt(i+1)))){
                cnt++;
                tmp = "";
            }
        }
        System.out.println(cnt);
    }
}