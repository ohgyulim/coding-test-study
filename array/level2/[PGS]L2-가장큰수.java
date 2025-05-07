import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String[] sNumbers = new String[numbers.length];

        for(int i = 0; i<numbers.length; i++){
            sNumbers[i] = String.valueOf(numbers[i]);
        }

        //a.compareTo(b)
        //a-b를 수행한 값 리턴
        //결과가 음수 (a < b) -> a가 앞
        //0 -> 유지
        //양수 (a > b) -> b가 앞
        Arrays.sort(sNumbers, (a,b) -> (b + a).compareTo(a + b));

        if(sNumbers[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();
        for(String s: sNumbers){
            sb.append(s);
        }
        return sb.toString();
    }
}