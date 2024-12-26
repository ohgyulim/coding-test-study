package implementation.level2;


import java.util.*;
import java.io.*;

public class BOJ_싸이버개강총회 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = timeToMinute(st.nextToken());
        int e = timeToMinute(st.nextToken());
        int q = timeToMinute(st.nextToken());

        Map<String, Integer> participatients = new HashMap<>();

        String chat;
        while((chat = br.readLine()) != null) {
            st = new StringTokenizer(chat);
            int time = timeToMinute(st.nextToken());
            String name = st.nextToken();

            if (time <= s && !participatients.containsKey(name)) {
                participatients.put(name, 1);
            }

            if (time >= e && time <= q && participatients.containsKey(name)) {
                participatients.put(name, 2);
            }
        }

        int answer = 0;
        for (Map.Entry<String, Integer> participatient : participatients.entrySet()) {
            if (participatient.getValue() == 2) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    public static int timeToMinute(String time) {
        String[]times = time.split(":");
        int hour = Integer.parseInt(times[0]) * 60;
        int minute = Integer.parseInt(times[1]);
        return hour+minute;
    }
}