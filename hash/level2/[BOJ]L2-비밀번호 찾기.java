import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //사이트 - 비밀번호
        Map<String, String> pwdMap = new HashMap<>();
        //맵에 정보 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String site = st.nextToken();
            String pwd = st.nextToken();
            pwdMap.put(site,pwd);
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            System.out.println(pwdMap.get(st.nextToken()));
        }

    }
}