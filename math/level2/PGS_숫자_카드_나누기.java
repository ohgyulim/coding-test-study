package math.level2;

public class PGS_숫자_카드_나누기 {
	public int solution(int[] arrayA, int[] arrayB) {
		int gcdA = getGCDForArray(arrayA);
		int gcdB = getGCDForArray(arrayB);

		int cdA = 0;
		int cdB = 0;

		// gcdA의 약수가 arrayB를 나누지 못하는지 확인
		for (int divisor = gcdA; divisor > 1; divisor--) {
			if (gcdA % divisor != 0) continue; // gcdA의 약수만 고려
			boolean dividesAll = true;
			for (int elem : arrayB) {
				if (elem % divisor == 0) {
					dividesAll = false;
					break;
				}
			}
			if (dividesAll) {
				cdA = divisor;
				break;
			}
		}

		// gcdB의 약수가 arrayA를 나누지 못하는지 확인
		for (int divisor = gcdB; divisor > 1; divisor--) {
			if (gcdB % divisor != 0) continue; // gcdB의 약수만 고려
			boolean dividesAll = true;
			for (int elem : arrayA) {
				if (elem % divisor == 0) {
					dividesAll = false;
					break;
				}
			}
			if (dividesAll) {
				cdB = divisor;
				break;
			}
		}

		if (cdA > cdB) return cdA;
		if (cdB > cdA) return cdB;
		return 0; // 둘 다 나눌 수 없는 경우
	}

	public static int getGCDForArray(int[] arr) {
		int gcd = arr[0];
		for (int i = 1; i < arr.length; i++) {
			gcd = getGCD(gcd, arr[i]);
		}
		return gcd;
	}

	public static int getGCD(int num1, int num2) {
		if (num2 == 0) {
			return num1;
		}
		return getGCD(num2, num1 % num2);
	}
}
