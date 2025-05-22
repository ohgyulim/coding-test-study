import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 10; i++){
            int n = Integer.parseInt(br.readLine());
            int count = 0;
            String target = br.readLine();
            String s = br.readLine();
            for(int j = 0; j < s.length(); j++){
                if(s.startsWith(target, j)){
                    count++;
                }
            }
            System.out.printf("#%d %d\n", n, count);
        }
    }
}
