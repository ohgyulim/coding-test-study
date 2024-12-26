import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = timeConverter(st.nextToken());
        int e = timeConverter(st.nextToken());
        int q = timeConverter(st.nextToken());

        Map<String,Integer> wait = new HashMap<>();
        Map<String,Integer> attend = new HashMap<>();

        String input;
        while ((input = br.readLine()) != null) {
            if (input.isEmpty()) break;

            st = new StringTokenizer(input);
            while (st.hasMoreTokens()) {
                int time = timeConverter(st.nextToken());
                String name = st.nextToken();

                if(time <= s){
                    wait.put(name,1);
                }else if(time >= e && time <= q && wait.containsKey(name)){
                    attend.put(name,1);
                }
            }
        }
        br.close();


        System.out.println(attend.size());
    }

    private static int timeConverter(String str){
        String[] splitedStr = str.split(":");
        return Integer.parseInt(splitedStr[0])*60 + Integer.parseInt(splitedStr[1]);
    }


}
