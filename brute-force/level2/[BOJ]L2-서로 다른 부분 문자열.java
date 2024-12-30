import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        Set<String> set = new HashSet<>();
        for(int i = 0; i < str.length(); i++){
            //substring은 j인덱스 전까지 끊기
            for(int j = i+1; j < str.length()+1; j++){
                set.add(str.substring(i,j));
            }
        }
        System.out.println(set.size());
    }

}

