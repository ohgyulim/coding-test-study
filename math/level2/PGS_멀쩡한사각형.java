package math.level2;

class PGS_멀쩡한사각형 {
    public long solution(int w, int h) {
        long answer = (long) w * (long) h;

        double child = 0;
        double parent = (double) w;


        long unavailable = 0;
        while (child < (long) h * (long) w) {
            double nextChild = child + (double) h;
            unavailable += Math.ceil(nextChild / parent) - Math.floor(child / parent);
            child = nextChild;
        }
        answer -= unavailable;
        return answer;
    }
}