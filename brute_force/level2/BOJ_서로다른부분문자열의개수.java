package brute_force.level2;

import java.util.*;
import java.io.*;

public class BOJ_서로다른부분문자열의개수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Set<String> subStringSet = new HashSet<>();

        for (int i=0; i<s.length(); i++) {
            for (int j=i+1; j<s.length()+1; j++) {
                subStringSet.add(s.substring(i,j));
            }
        }
        System.out.println(subStringSet.size());
    }
}
