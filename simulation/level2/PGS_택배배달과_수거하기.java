package simulation.level2;

import java.util.*;

public class PGS_택배배달과_수거하기 {
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;

		List<Integer> deliveryDistances = new ArrayList<>();
		List<Integer> pickupDistances = new ArrayList<>();

		for (int i = n; i >= 1; i--) {
			int deliveryDistance = deliveries[i-1];
			int pickupDistance = pickups[i-1];
			for (int j = 1; j <= deliveryDistance; j++) {
				deliveryDistances.add(i);
			}
			for (int j = 1; j <= pickupDistance; j++) {
				pickupDistances.add(i);
			}
		}
		int deliveryDistSize = deliveryDistances.size();
		int pickupDistSize = pickupDistances.size();
		int size = Math.max(deliveryDistSize, pickupDistSize);

		for (int i = 0; i < size; i += cap) {
			int deliveryDistance = (deliveryDistSize < i + 1) ? 0 : deliveryDistances.get(i);
			int pickupDistance = (pickupDistSize < i + 1) ? 0 : pickupDistances.get(i);
			answer += Math.max(deliveryDistance, pickupDistance) * 2;
		}

		return answer;
	}
}
