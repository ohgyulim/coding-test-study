import java.util.*;
class Solution {
    public String solution(String s) {
        //-1 중요 (마지막 빈 문자열까지 포함)
        String[] sArr = s.toLowerCase().split(" ",-1);
        StringBuilder sb = new StringBuilder();
        for(String str : sArr){
            if(!str.isEmpty()){
                sb.append(str.substring(0,1).toUpperCase());
                sb.append(str.substring(1));
            }
            sb.append(" ");
        }
        String answer = sb.toString();
        return answer.substring(0,answer.length()-1);
    }
}