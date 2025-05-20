import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String,Integer> map = new HashMap<>();
        String answer = "";
        for(String s : completion){
            map.put(s,map.getOrDefault(s,0)+1);
        }

        for(String s: participant){
            if(map.getOrDefault(s, 0) == 0) answer = s;
            if(map.getOrDefault(s,0) > 0) map.put(s,map.getOrDefault(s,0)-1);

        }
        return answer;
    }
}