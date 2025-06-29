import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for(int i = 0; i < N; i++){
            words[i] = br.readLine();
        }
        int cnt = 0;

        int[] word1 = new int[26];
        String str = words[0];
        for(int j = 0; j < str.length(); j++){
            word1[str.charAt(j)-'A']++;
        }


        for(int j = 1; j < N; j++){
            int diffCnt = 0;
            int totalDiff = 0;
            //비교할 배열 분해
            int[] word2 = new int[26];
            String str2 = words[j];
            for(int k = 0; k < str2.length(); k++){
                word2[str2.charAt(k)-'A']++;
            }

            //차이 분석
            for(int k = 0; k < 26; k++){
                if(word1[k] != word2[k]){
                    diffCnt++;
                    totalDiff+= Math.abs(word1[k]-word2[k]);
                }
            }

            if(diffCnt == 0 ||
                    (diffCnt == 1 && totalDiff == 1) ||
                    (diffCnt == 2 && totalDiff == 2 &&
                            (Math.abs(str.length()-str2.length()) <= 1))){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}