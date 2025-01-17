import java.util.*;

public class k진수에서_소수_개수_구하기 {
    public int solution(int n, int k) {
        int answer = 0;

        String changeN = Integer.toString(n,k); //진법 변환 n을 -> k진수로
        // System.out.println(changeN);

        StringTokenizer st = new StringTokenizer(changeN, "0");
        while(st.hasMoreTokens()){
            long num = Long.parseLong(st.nextToken()); //11111111111111 와 같이 int 범위 주의
            // System.out.println(num);
            if(isPrime(num)){
                answer++;
            }
        }

        return answer;
    }

    //소수 판별
    public boolean isPrime(long num){
        if(num<=1){
            return false;
        }
        for(int i=2; i<=Math.sqrt(num); i++){
            if(num%i ==0){
                return false;
            }
        }
        return true;
    }
}
