package recursion.level2;


public class PGS_유사_칸토어_비트열 {
	public int solution(int n, long l, long r) {
		// (0 ~ r 까지 1의 개수) - ( 0 ~ l-1 까지 1의 개수) = l ~ r 구간 까지의 1의 개수
		return (int)(count(n, r) - count(n, l-1));
	}

	// 0 ~ k 까지 1의 개수 구하기
	private long count(int n, long curNumber) {
		if (n == 0) {
			return 1;
		}

		long preBitStringNumber = n - 1;
		// 현재 n에 대해 5등분했을 때, 하나의 덩어리가 가지고 있는 숫자
		// ex) n = 4 (총 625) -> 한 덩어리: 125
		long division = (long) Math.pow(5, preBitStringNumber);
		// 5등분 중 한 덩어리가 가지고 있는 1의 개수: 64
		long numberOfOne = (long) Math.pow(4, preBitStringNumber);

		// 현재 위치 = 현재 숫자 / 한 덩어리
		// ex) 625를 5등분 했을 때 118(curNumber)의 위치 = 0번째
		//     125, 125, 125, 125, 125
		long zone = (int) (curNumber / division);
		if((curNumber % division) == 0) zone--;

		if (zone == 2) {
			// 0만 있는 구역
			return numberOfOne * zone;
		} else if (zone < 2) {
			// 0이 있는 구역(zone = 2인구역) 전 구역
			// 0번째 zone ~ zone - 1 까지 1의 개수 = numberOfOne * zone
			// 현재 zone ~ curNum 까지 1의 개수 = count(n - 1, curNumber - (division * zone));
			// 0번째 zone 까지의 덩어리 숫자 = division * zone (division이 125 일 때, 2번째 덩어리 = 125 * 2 = 250)
			// curNumber - (division * zone) -> 현재 숫자에서 직전의 덩어리까지 처리했기 때문에 뺌
			return numberOfOne * zone + count(n - 1, curNumber - (division * zone));
		} else {
			// 0이 있는 구역 (zone = 2인구역) 이후 구역
			// 앞에 0인 구역이 있으므로 1의 개수를 구할 때 (zone - 1) 값을 곱함
			return numberOfOne * (zone - 1) + count(n - 1, curNumber - (division * zone));
		}
	}
}
