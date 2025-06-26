import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        //비교할 자리수
        int idx = 0;
        int num = 1;

        //1 부터 숫자를 늘려가며 해당 숫자의 각 자리수와 S의 현재 비교 인덱스가 일치하면
        //idx를 올려 S의 다음 자리수로 넘어감
        //반복이 한 번 끝나면 수를 1씩 증가시켜 다음수를 비교

        while(idx < str.length()){
            String current = String.valueOf(num);
            for(int i = 0; i < current.length();i++){
                if(idx < str.length() && str.charAt(idx) == current.charAt(i)){
                    idx++;
                }
            }
            num++;
        }
        System.out.println(num-1);


    }
}