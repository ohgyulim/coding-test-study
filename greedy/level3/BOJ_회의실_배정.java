package greedy.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_회의실_배정 {

    private static class Meeting {
        int start,end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        ArrayList<Meeting> meetings = new ArrayList<>(N);

        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.add(new Meeting(start, end));
        }

        // TODO: 회의 끝나는 시간 기준으로 오름차 정렬
        Collections.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                if (o2.end == o1.end){
                    return o1.start - o2.start;
                }
                return o1.end - o2.end;
            }
        });

        int cnt = 0;
        int preEndTime = 0;

        for (int i=0; i<meetings.size();i++){
            Meeting curr = meetings.get(i);
            if (preEndTime <= curr.start) {
                preEndTime = curr.end;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
