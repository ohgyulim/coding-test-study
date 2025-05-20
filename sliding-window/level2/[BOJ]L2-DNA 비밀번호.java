import java.util.*;
import java.io.*;

public class Main{
    static int[] nowArr;
    static int[] checkArr;
    static int check;

    public static void main(String[] agrs) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        char[] str = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine());
        checkArr = new int[4];
        nowArr = new int[4];
        check = 0;
        int answer = 0;
        for(int i = 0; i < 4; i++){
            checkArr[i] = Integer.parseInt(st.nextToken());
            if(checkArr[i]==0) check++;
        }

        for(int i = 0; i < P; i++){
            add(str[i]);
        }

        if(check == 4) answer++;

        for(int i = P; i < S; i++){
            int j = i-P;
            add(str[i]);
            remove(str[j]);
            if(check==4) answer++;
        }

        System.out.println(answer);
    }

    private static void add(char c){
        switch(c){
            case 'A':
                nowArr[0]++;
                if(nowArr[0] == checkArr[0]) check++;
                break;
            case 'C':
                nowArr[1]++;
                if(nowArr[1] == checkArr[1]) check++;
                break;
            case 'G':
                nowArr[2]++;
                if(nowArr[2] == checkArr[2]) check++;
                break;
            case 'T':
                nowArr[3]++;
                if(nowArr[3] == checkArr[3]) check++;
                break;
        }
    }

    private static void remove(char c){
        switch(c){
            case 'A':
                if(nowArr[0] == checkArr[0]) check--;
                nowArr[0]--;
                break;
            case 'C':
                if(nowArr[1] == checkArr[1]) check--;
                nowArr[1]--;
                break;
            case 'G':
                if(nowArr[2] == checkArr[2]) check--;
                nowArr[2]--;
                break;
            case 'T':
                if(nowArr[3] == checkArr[3]) check--;
                nowArr[3]--;
                break;
        }
    }
}