import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<String> set = new HashSet();
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            if(!set.contains(str)) set.add(str);
        }

        String[] keywords = new String[10];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++){
            keywords = br.readLine().split(",");
            for (int j = 0; j < keywords.length; j++){
                if(set.contains(keywords[j])) set.remove(keywords[j]);
            }
            sb.append(set.size()+"\n");
        }
        System.out.println(sb.toString().trim());
    }
}