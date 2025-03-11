package math.level2;

// 정답봄
// 최대 공약수 만큼 대각선들이 반복
// w' = w / 최대 공약수
// h' = h / 최대 공약수
// (h' - 1) = 시작(1)부터 대각선 만큼 바뀐 y값들
// (w' - 1) = 시작(1)부터 대각선 만큼 바뀐 x값들
// + 1 (시작점 포함)
// 최대 공약수 * (1 + (h' - 1) + (w' - 1)) = h + w - 최대공약수
public class PGS_멀쩡한_사각형 {
	public long solution(int w, int h) {
		long total = (long) w * h;
		int gcd = gcd(w, h);
		long removed = w + h - gcd;
		return total - removed;
	}

	private int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
