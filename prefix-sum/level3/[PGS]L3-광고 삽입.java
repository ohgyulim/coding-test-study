import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playSec = toSeconds(play_time);
        int advSec = toSeconds(adv_time);
        int[] times = new int[playSec + 1];

        for (String log : logs) {
            String[] splitLog = log.split("-");
            int start = toSeconds(splitLog[0]);
            int end = toSeconds(splitLog[1]);
            times[start] += 1;
            times[end] -= 1;
        }

        for (int i = 1; i <= playSec; i++) {
            times[i] += times[i - 1];
        }

        for (int i = 1; i <= playSec; i++) {
            times[i] += times[i - 1];
        }

        long maxView = times[advSec - 1];
        int startTime = 0;

        for (int i = advSec; i <= playSec; i++) {
            long currentView = times[i] - times[i - advSec];
            if (currentView > maxView) {
                maxView = currentView;
                startTime = i - advSec + 1;
            }
        }

        return toTimeFormat(startTime);
    }

    private int toSeconds(String time) {
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int s = Integer.parseInt(parts[2]);
        return h * 3600 + m * 60 + s;
    }

    private String toTimeFormat(int seconds) {
        int h = seconds / 3600;
        int m = (seconds % 3600) / 60;
        int s = seconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}