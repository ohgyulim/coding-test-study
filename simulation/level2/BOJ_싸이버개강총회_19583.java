package simulation.level2;

import java.util.*;
import java.io.*;

public class BOJ_싸이버개강총회_19583 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = calcTime(st.nextToken());
        int end = calcTime(st.nextToken());
        int streamingEnd = calcTime(st.nextToken());
        Map<String, Integer> firstChatTimeMap = new HashMap<>();
        Set<String> checkedNicknames = new HashSet<>();
        String chatInfo;
        while ((chatInfo = br.readLine()) != null) {
            st = new StringTokenizer(chatInfo);
            int time = calcTime(st.nextToken());
            String nickname = st.nextToken();
            if (!firstChatTimeMap.containsKey(nickname)) {
                firstChatTimeMap.put(nickname, time);
            }

            if (time >= end && time <= streamingEnd && firstChatTimeMap.get(nickname) <= start) {
                checkedNicknames.add(nickname);
            }
        }
        System.out.println(checkedNicknames.size());
    }

    static private int calcTime(String time) {
        String[] timeArray = time.split(":");
        return 60 * Integer.parseInt(timeArray[0]) + Integer.parseInt(timeArray[1]);
    }
}
