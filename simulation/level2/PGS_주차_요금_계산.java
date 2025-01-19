package simulation.level2;

import java.util.*;
import java.text.*;

public class PGS_주차_요금_계산 {
	int basicTime;
	int basicMoney;
	int perTime;
	int perMoney;

	public int[] solution(int[] fees, String[] records) {
		Map<String, Date> enteredCars = new HashMap<>();
		Map<String, Integer> carTimes = new TreeMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		basicTime = fees[0];
		basicMoney = fees[1];
		perTime = fees[2];
		perMoney = fees[3];

		for (String record : records) {
			String[] history = record.split(" ");
			try {
				Date time = sdf.parse(history[0]);
				String carNumber = history[1];
				if (enteredCars.containsKey(carNumber)) {
					Date enterTime = enteredCars.get(carNumber);
					int diff = (int)(time.getTime() - enterTime.getTime()) / (1000 * 60);
					carTimes.put(carNumber, carTimes.getOrDefault(carNumber, 0) + diff);
					enteredCars.remove(carNumber);
				} else {
					enteredCars.put(carNumber, time);
				}
			} catch (Exception e) {
				continue;
			}
		}

		for (var entrySet : enteredCars.entrySet()) {
			String carNumber = entrySet.getKey();
			try {
				Date time = entrySet.getValue();
				int diff = (int)(sdf.parse("23:59").getTime() - time.getTime()) / (1000 * 60);
				carTimes.put(carNumber, carTimes.getOrDefault(carNumber, 0) + diff);
			} catch(Exception e) {
				continue;
			}
		}

		int index = 0;
		int[] answer = new int[carTimes.size()];
		for (var entrySet : carTimes.entrySet()) {
			answer[index++] = getMoney(entrySet.getValue());
		}

		return answer;
	}

	public int getMoney(int diff) {
		int money = basicMoney;

		diff = Math.max(0, (diff - basicTime));
		money += (diff / perTime) * perMoney;
		money += diff % perTime > 0 ? perMoney : 0;
		return money;
	}
}
