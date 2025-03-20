import java.util.*;

class Solution {
    public int[] solution(String s) {
        int cnt = 0;
        int zeroSum = 0;
        while(!s.equals("1")){
            int before = s.length();
            s = s.replaceAll("0","");
            zeroSum += before - s.length();
            s = convert(s.length(), new StringBuilder());
            cnt++;
        }
        return new int[]{cnt, zeroSum};
    }

    private String convert(int x, StringBuilder sb){
        sb.append(x%2);
        if(x/2 != 0){
            convert(x/2, sb);
        }
        return sb.reverse().toString();
    }
}