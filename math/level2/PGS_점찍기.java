package math.level2;

public class PGS_점찍기 {
    public long solution(int k, long d) {
        long answer = 0;

        for (long r = 0; r <= d; r += k) {
            long value = (long) Math.sqrt(d * d - r * r);
            answer += value / k + 1;
        }
        return answer;
    }
}
