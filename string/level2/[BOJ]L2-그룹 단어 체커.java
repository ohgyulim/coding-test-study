import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = 0;
        boolean isGroup;
        for(int i = 0; i < n; i++){
            Set<Character> set = new HashSet<>();
            isGroup = true;
            String word = br.readLine();
            char prev = 0;
            for(int j = 0; j < word.length(); j++){
                char curr = word.charAt(j);
                if(curr != prev){
                    if(set.contains(curr)) {
                        isGroup = false;
                        break;
                    }else{
                        set.add(curr);
                    }
                }
                prev = curr;
            }
            if(isGroup) count++;
        }
        System.out.println(count);
    }
}