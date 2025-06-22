import java.util.*;
import java.io.*;

class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] switches = new boolean[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= n; i++){
            String s = st.nextToken();
            if(s.equals("0")) switches[i] = false;
            else switches[i] = true;
        }

        int student = Integer.parseInt(br.readLine());

        for(int j = 0; j < student; j++){
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            if(gender == 1){
                for(int i = 1; i <= n/number; i++){
                    switches[i*number] = !switches[i*number];
                }
            }else{
                switches[number] = !switches[number];
                for(int i = 1 ; i<=n; i++){
                    if(number-i > 0 && number+i <= n
                            && switches[number-i] == switches[number+i]){
                        switches[number-i] = !switches[number-i];
                        switches[number+i] = !switches[number+i];
                    }else{
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++){

            sb.append(switches[i] ? "1" : "0").append(" ");
            if(i%20 == 0) sb.append("\n");
        }
        System.out.println(sb.toString().trim());
    }
}