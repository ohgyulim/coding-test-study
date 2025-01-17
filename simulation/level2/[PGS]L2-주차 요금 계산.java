import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        Map<String, Integer> parkingTime = new HashMap<>();
        Map<String, Integer> inTime = new HashMap<>();

        for (String record : records) {
            String[] parts = record.split(" ");
            String time = parts[0];
            String carNumber = parts[1];
            String action = parts[2];

            int minutes = convertToMinutes(time);
            if (action.equals("IN")) {
                inTime.put(carNumber, minutes);
            } else {
                int parkedTime = minutes - inTime.remove(carNumber);
                parkingTime.put(carNumber, parkingTime.getOrDefault(carNumber, 0) + parkedTime);
            }
        }

        for (String carNumber : inTime.keySet()) {
            int parkedTime = convertToMinutes("23:59") - inTime.get(carNumber);
            parkingTime.put(carNumber, parkingTime.getOrDefault(carNumber, 0) + parkedTime);
        }

        return parkingTime.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .mapToInt(entry -> calculateFee(entry.getValue(), baseTime, baseFee, unitTime, unitFee))
                .toArray();
    }

    private int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    private int calculateFee(int parkedTime, int baseTime, int baseFee, int unitTime, int unitFee) {
        if (parkedTime <= baseTime) {
            return baseFee;
        }
        return baseFee + (int) Math.ceil((double) (parkedTime - baseTime) / unitTime) * unitFee;
    }
}
