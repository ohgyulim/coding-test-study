package implementation.level2;


import java.util.*;
import java.io.*;
import java.util.Map.Entry;

public class BOJ_걸그룹_마스터_준석이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        TreeMap<String, String> girlgroups = new TreeMap<>();
        for (int i=0;i<N;i++) {
            String group = br.readLine();
            int cnt = Integer.parseInt(br.readLine());
            for (int j=0;j<cnt;j++) {
                girlgroups.put(br.readLine(), group);
            }
        }

        for (int t=0;t<M;t++) {
            String target = br.readLine();
            int cmd = Integer.parseInt(br.readLine());
            if (cmd == 0) {

                for (Entry<String, String> entry : girlgroups.entrySet()) {
                    if (entry.getValue().equals(target)) {
                        System.out.println(entry.getKey());
                    }
                }


            }
            else {
                System.out.println(girlgroups.get(target));
            }
        }

    }
}
