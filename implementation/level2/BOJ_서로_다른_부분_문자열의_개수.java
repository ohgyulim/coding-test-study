package implementation.level2;


import java.util.*;
import java.io.*;

public class BOJ_서로_다른_부분_문자열의_개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        HashSet<String> section = new HashSet<>();
        for (int len=0;len<s.length();len++) {
            for (int j=0;j<=s.length()-len;j++) {
                section.add(s.substring(j, j+len));
            }
        }

        System.out.println(section.size());
    }
}
