import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //그룹 - 멤버리스트
        Map<String, List<String>> groupMap = new HashMap<>();
        //멤버 - 그룹
        Map<String, String> memberMap = new HashMap<>();

        // 그룹 정보 입력
        for (int i = 0; i < n; i++) {
            // 그룹명
            String group = br.readLine().trim();
            int k = Integer.parseInt(br.readLine().trim());

            List<String> members = new ArrayList<>();
            //멤버 입력
            for (int j = 0; j < k; j++) {
                String member = br.readLine().trim();
                members.add(member);
                //멤버-그룹 매핑
                memberMap.put(member, group);
            }
            //사전순
            Collections.sort(members);
            //그룹-멤버 매핑
            groupMap.put(group, members);
        }

        for (int i = 0; i < m; i++) {
            String question = br.readLine().trim();
            int qType = Integer.parseInt(br.readLine().trim());

            //그룹명으로 멤버 목록 출력
            if (qType == 0) {
                if (groupMap.containsKey(question)) {
                    List<String> members = groupMap.get(question);
                    for (String member : members) {
                        System.out.println(member);
                    }
                }
                //멤버명으로 그룹 출력
            } else {
                if (memberMap.containsKey(question)) {
                    System.out.println(memberMap.get(question));
                }
            }
        }
    }
}
