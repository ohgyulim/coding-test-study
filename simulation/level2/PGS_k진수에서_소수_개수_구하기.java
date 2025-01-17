package simulation.level2;

public class PGS_k진수에서_소수_개수_구하기 {
	public int solution(int n, int k) {
		int answer = 0;

		String convertedStr = Integer.toString(n, k);
		String[] convertedNumbers = convertedStr.split("0");
		long[] numbers = new long[convertedNumbers.length];

		for (int i = 0; i < convertedNumbers.length; i++) {
			try {
				numbers[i] = Long.parseLong(convertedNumbers[i]);

			} catch(Exception e) {
				continue;
			}

			if (numbers[i] < 2) continue;

			boolean isPrime = true;
			for (long j = 2; j <= Math.sqrt(numbers[i]); j++) {
				if (numbers[i] % j == 0) isPrime = false;
			}

			if (isPrime) answer += 1;
		}


		return answer;
	}
}
