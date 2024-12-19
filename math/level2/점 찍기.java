class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long y = 0;
        for(int i = 0; i <= d; i+=k){
            y = (long)Math.sqrt(Math.pow(d,2) - Math.pow(i,2));
            answer += (y/k)+1;
        }
        return answer;
    }
}