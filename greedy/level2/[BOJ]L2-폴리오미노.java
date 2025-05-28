import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        //replaceAll은 정규표현식이 들어가야 하지만 특수문자가 없다면 문자열 그대로 바꿀 수 있음
        //String은 값 자체만 바꿀 순 없는 불변객체이기 때문에 다시 담아줘야함
        str = str.replaceAll("XXXX","AAAA");
        str = str.replaceAll("XX","BB");

        if(str.contains("X")) System.out.println(-1);
        else System.out.println(str);
    }
}