public class 멀쩡한_사각형 {
    public long solution(int w, int h) {
        long answer = 1;

        if(w<h){
            int temp = w;
            w = h;
            h = temp;
        }

        long gcd = getGCD(w,h);
        // System.out.println(gcd);

        return (long)w * (long)h - (w + h - gcd);   //규칙
    }

    public long getGCD(int a, int b){
        if(a%b == 0){
            return b;
        }

        return getGCD(b, a%b);
    }
}
