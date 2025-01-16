package hash.level2;

import java.util.*;

class PGS_주차요금계산 {
    int defaultTime;
    int defaultFee;
    int unitTime;
    int unitFee;

    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        defaultTime = fees[0];
        defaultFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];


        Map<Integer, Integer> timeTable = new HashMap<>(); //차량번호:입차시간(분)
        Map<Integer, Integer> accTime = new HashMap<>(); // 차량번호:누적시간


        for (String record : records) {
            String[] recordArray = record.split(" ");

            int number = Integer.parseInt(recordArray[1]);
            String command = recordArray[2];
            String[] timeArray = recordArray[0].split(":");
            int time = Integer.parseInt(timeArray[0]) * 60 + Integer.parseInt(timeArray[1]);

            if (command.equals("IN")) {
                timeTable.put(number, time);
            } else {
                int duringTime = time - timeTable.get(number);
                accTime.put(number, accTime.getOrDefault(number, 0) + duringTime);
                timeTable.remove(number);
            }
        }

        int lastTime = 23 * 60 + 59;
        for (int number : timeTable.keySet()) {
            int duringTime = lastTime - timeTable.get(number);
            accTime.put(number, accTime.getOrDefault(number, 0) + duringTime);
        }

        List<Integer> numbers = new ArrayList<>();
        for (int number : accTime.keySet()) {
            numbers.add(number);
        }

        Collections.sort(numbers);
        answer = new int[numbers.size()];

        int idx = 0;
        for (int number : numbers) {
            answer[idx++] = calcCost(accTime.get(number));
        }

        return answer;
    }

    private int calcCost(int duringTime) {
        return (int) Math.ceil(Math.max(duringTime - defaultTime, 0) / (double) unitTime) * unitFee + defaultFee;
    }
}