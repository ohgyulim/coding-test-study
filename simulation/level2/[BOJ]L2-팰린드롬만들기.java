import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] alpha = new int[26];
        char oddChar = 0;
        int oddCnt = 0;
        for(char c : str.toCharArray()){
            alpha[c-'A']++;
        }

        int idx = -1;
        for(int i : alpha){
            idx++;
            if(i%2 != 0) {
                oddCnt++;
                oddChar = (char) (idx + 'A');
            }
            if(oddCnt > 1) {
                System.out.println("I'm Sorry Hansoo");
                return;
            }
        }
        StringBuilder left = new StringBuilder();
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < 26; i++){
            for(int j = 0; j < alpha[i]/2 ; j++){
                left.append((char)(i+'A'));
            }
        }

        result.append(left);
        if(oddCnt == 1) result.append(oddChar);
        result.append(left.reverse());

        System.out.println(result.toString());
    }
}