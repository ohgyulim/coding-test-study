class Solution {
    public int solution(int n) {
        int answer = 0;
        //k개의 연속된 수의 합 = k(k+1)/2
        //
        for(int i = 1; i*(i+1)/2 <= n; i++){
            if((n-(i*(i-1)/2)) % i == 0){
                answer++;
            }
        }
        return answer;
    }
}