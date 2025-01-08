import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String,Integer> map = new HashMap<>();


        for(int i = 0; i < want.length; i++){
            map.put(want[i],number[i]);
        }
        for(int i = 0; i <= discount.length-10; i++){
            Map<String,Integer> tempMap = new HashMap<>();
            boolean flag = true;
            for(int j = 0; j < 10; j++){
                tempMap.put(discount[i+j],tempMap.getOrDefault(discount[i+j],0)+1);
            }
            for(String key : tempMap.keySet()){
                if(map.get(key) != tempMap.get(key)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                answer++;
            }
        }


        return answer;
    }
}