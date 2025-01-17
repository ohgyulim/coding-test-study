package implementation.level2;


import java.util.*;

class PGS_주차_요금_계산 {

    private String LAST_OUT_TIME = "11:59";

    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        Map<String,Integer> in = new TreeMap<>();
        Map<String, Integer> cost = new TreeMap<>();

        for (int i=0;i<records.length;i++) {
            StringTokenizer st = new StringTokenizer(records[i]);
            int time = hourToMin(st.nextToken());
            String carNum = st.nextToken();
            String status = st.nextToken();

            if (status.equals("IN")) {

                if (!in.containsKey(carNum)) { // 처음 입차된 차
                    in.put(carNum, time);
                    cost.put(carNum, 0);
                    continue;
                }

                // 두번 이상 입차 (입차시간 갱신 + 지금까지 비용 계산)
                int timeSpent = time - in.get(carNum); // 머문 시간
                if (timeSpent <= fees[0]) {
                    cost.put(carNum, cost.get(carNum) + fees[1]);
                }
                else {


                }

            }
            else {
                //int lastInTime = in.get(carNum);


            }
        }

        return answer;
    }

    public int hourToMin(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken()) * 60;
        int min = Integer.parseInt(st.nextToken());
        return hour + min;
    }
}