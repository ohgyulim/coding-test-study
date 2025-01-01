import java.io.*;
import java.util.*;

public class _9375_패션왕_신해빈 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            int N = Integer.parseInt(br.readLine());
            int result = 1;

            Map<String,Integer> map = new HashMap<>();

            for(int i=0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String item = st.nextToken();
                String type = st.nextToken();
                map.put(type,map.getOrDefault(type,0)+1);
            }

            for(String key : map.keySet()){
                result *= (map.get(key)+1);
            }
            System.out.println(result-1);
        }
    }
}