import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        int cnt = 0;
        //Map의 Value값을 기준으로 정렬
        Map<Integer,Integer> map = new HashMap<>();
        for(int t : tangerine){
            if(map.get(t) != null){
                map.put(t,map.get(t)+1);
            }else{
                map.put(t,1);
            }
        }
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((o1,o2) -> Integer.compare(o2.getValue(), o1.getValue()));
        //정렬된 과일(갯수가 많은수부터)을 한종류씩 전부 꺼내서
        //k개인지 확인
        for(Map.Entry<Integer,Integer> e : entries){
            cnt += e.getValue();
            answer++;
            if(cnt >= k){
                break;
            }
        }

        return answer;
    }
}