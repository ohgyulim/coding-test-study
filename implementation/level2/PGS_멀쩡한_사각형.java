package implementation.level2;

class PGS_멀쩡한_사각형 {
    public long solution(int w, int h) {
        int d = getGcd(w, h);
        int nw = w / d, nh = h / d;
        return (long)w * h - (nw + nh - 1) * d;
    }

    int getGcd(int w, int h) {
        if (h == 0) return w;
        return getGcd(h, w % h);
    }
}