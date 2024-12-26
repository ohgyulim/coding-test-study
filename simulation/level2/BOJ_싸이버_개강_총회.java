import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String startTime = st.nextToken(); // 개강총회 시작
        String endTime = st.nextToken();   // 개강총회 종료
        String streamEndTime = st.nextToken(); // 스트리밍 종료

        // 입장, 퇴장 확인
        Set<String> enteredMembers = new HashSet<>();
        Set<String> confirmedMembers = new HashSet<>();

        String input;
        
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            String[] log = input.split(" ");
            String time = log[0];
            String nickname = log[1];

            // 입장 확인
            if (time.compareTo(startTime) <= 0) {
                enteredMembers.add(nickname);
            }

            // 퇴장 확인
            if (time.compareTo(endTime) >= 0 && time.compareTo(streamEndTime) <= 0) {
                if (enteredMembers.contains(nickname)) {
                    confirmedMembers.add(nickname);
                }
            }
        }

        // 최종 출력
        System.out.println(confirmedMembers.size());
    }
}