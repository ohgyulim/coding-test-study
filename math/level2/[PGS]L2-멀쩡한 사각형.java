// class Solution {
//     public long solution(int w, int h) {
//         long answer = 0;
//         double inclination = (double)h/(double)w;;

//         for(int i = 1; i < w; i++){
//             answer += (long)(inclination * i);
//         }
//         answer *= 2;

//         return answer;
//     }
// }

class Solution {
    public long solution(int w, int h) {
        long total = (long) w * h;
        long pass = w + h - gcd(w, h);
        return total - pass;
    }


    private long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }
}
