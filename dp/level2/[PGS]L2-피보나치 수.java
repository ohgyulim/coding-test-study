class Solution {
    //50넘어가서 재귀 시간초과
//     public int solution(int n) {
//         return F(n)%1234567;
//     }

//     private int F(int n){
//         if(n == 1) return 1;
//         if(n <= 0) return 0;
//         return F(n-1)+F(n-2);
//     }

    //F(n) % m = (F(n-1)+F(n-2)) % m
    //=> F(n-1) % m + F(n-2) % m
    // 각 단계마다 나눠도 동일함 2만 번째 부터는 long범위도 넘어가서 미리 줄여야함
    public int solution(int n) {
        int[] answer = new int[n+1];
        answer[0] = 0;
        answer[1] = 1;++
        for(int i = 2; i <= n ; i++){
            answer[i] = answer[i-1] % 1234567 +answer[i-2] % 1234567;
        }
        return answer[n]%1234567;
    }
}