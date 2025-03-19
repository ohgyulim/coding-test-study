import java.util.*;
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String convertedN = "";
        String[] targets = new String[n];
        StringBuilder sb = new StringBuilder();

        //진수 변환
        sb = convert(new StringBuilder(),n,k);
        convertedN=sb.reverse().toString();
        System.out.println(convertedN);

        //0기준 스플릿
        targets = convertedN.split("0");

        //형변환해서 소수 판별
        for(String target : targets){
            if(target.equals("")){
                continue;
            }
            long num = Long.parseLong(target);
            if(num > 1 && isPrime(num)){
                answer++;
            }

        }

        return answer;
    }

    private StringBuilder convert(StringBuilder sb, long n, int k){
        sb.append(n%k);
        if(n/k != 0){
            sb = convert(sb,n/k,k);
        }

        return sb;
    }

    private boolean isPrime(long num){
        for(long i=2; i <= (long)Math.sqrt(num); i++){
            if(num%i == 0){
                return false;
            }
        }
        return true;
    }
}