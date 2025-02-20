package prefix_sum.level3;

import java.util.*;

class PGS_광고삽입 {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        String[] play_time_array = play_time.split(":");
        String[] adv_time_array = adv_time.split(":");
        int playTime = calcTime(play_time_array);
        int advTime = calcTime(adv_time_array);

        int[] playTimeTable = new int[playTime + 2];

        for (String log : logs) {
            StringTokenizer st = new StringTokenizer(log, "-");
            String[] start = st.nextToken().split(":");
            String[] end = st.nextToken().split(":");

            int startTime = calcTime(start);
            int endTime = calcTime(end);

            playTimeTable[startTime]++;
            playTimeTable[endTime]--;
        }

        for (int i = 1; i <= playTime; i++) {
            playTimeTable[i] += playTimeTable[i - 1];
        }

        long time = 0;
        for (int i = 0; i < advTime; i++) {
            time += playTimeTable[i];
        }

        int left = 0;
        int right = advTime - 1;
        int answerIdx = 0;
        long mxTime = time;
        while (right <= playTime - 1) {
            time -= playTimeTable[left++];
            time += playTimeTable[++right];
            if (time > mxTime) {
                answerIdx = left;
                mxTime = time;
            }

        }
        int hour = answerIdx / 3600;
        int minute = answerIdx % 3600 / 60;
        int second = answerIdx % 3600 % 60;

        String hourString = hour < 10 ? "0" + hour : "" + hour;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        String secondString = second < 10 ? "0" + second : "" + second;

        answer = hourString + ":" + minuteString + ":" + secondString;
        return answer;
    }

    private int calcTime(String[] time) {
        return Integer.parseInt(time[0]) * 3600 + Integer.parseInt(time[1]) * 60 + Integer.parseInt(time[2]);
    }
}