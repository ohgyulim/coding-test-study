package greedy.level2;

import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] alpha = new int['Z'-'A'+1];
        for (int n=0; n<N; n++) {
            String word = br.readLine();
            int unit = (int)Math.pow(10,word.length()-1);
            for (int i=0; i<word.length(); i++) {
                alpha[word.charAt(i) - 'A'] += unit;
                unit /= 10;
            }
        }

        Arrays.sort(alpha);
        int answer = 0;
        int num = 9;
        for (int i=alpha.length-1; i>=alpha.length-10; i--) {
            answer += alpha[i] * num;
            num--;
        }

        System.out.println(answer);
    }
}
