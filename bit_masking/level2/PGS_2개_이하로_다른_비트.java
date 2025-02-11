package bit_masking.level2;

public class PGS_2개_이하로_다른_비트 {
	public long[] solution(long[] numbers) {
		long[] answer = new long[numbers.length];

		for (int i = 0; i < numbers.length; i++) {
			long number = numbers[i];

			if ((number & 1) == 0) {
				answer[i] = number + 1;
			} else {
				long mask = 1L;
				// ex) number = 1011일때, mask = 100
				while ((number & mask) != 0) {
					mask <<= 1;
				}
				// number(1011) + mask(0100) = 1111
				// mask >> 1 = 0010
				// 1111 - 0010 = 1101
				answer[i] = number + mask - (mask >> 1);
			}
		}
		return answer;
	}
}
