package brute_force.level2;

class PGS_두원사이의정수쌍 {
    public long solution(long r1, long r2) {
        long answer = 0;
        for (long i = 1; i <= r2; i++) {
            long y1 = 0L;
            long y2;
            if (i <= r1) {
                y1 = (long) Math.ceil(Math.sqrt(r1 * r1 - i * i));
            }
            y2 = (long) Math.floor(Math.sqrt(r2 * r2 - i * i));

            answer += y2 - y1 + 1;
        }
        return answer*4;
    }
}