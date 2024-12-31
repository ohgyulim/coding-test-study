package hash.level2;

import java.util.*;
import java.io.*;

public class PGS_걸그룹마스터준석이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        Map<String, List<String>> teamMap = new HashMap<>();
        Map<String, String> memberMap = new HashMap<>();
        for (int n=0; n<N; n++) {
            String team = br.readLine();
            int nop = Integer.parseInt(br.readLine()); // Number Of People
            List<String> teammate = teamMap.getOrDefault(team, new ArrayList<>());
            for (int i=0; i<nop; i++) {
                String teamone = br.readLine();
                teammate.add(teamone);
                memberMap.put(teamone, team);
            }
            teamMap.put(team, teammate);
        }

        for (int m=0; m<M; m++) {
            String name = br.readLine();
            int type = Integer.parseInt(br.readLine());
            if (type == 0) {
                List<String> teammate = teamMap.get(name);
                Collections.sort(teammate);
                for (String teamone : teammate) {
                    System.out.println(teamone);
                }
            } else {
                System.out.println(memberMap.get(name));
            }
        }
    }
}
